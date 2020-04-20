package cz.ear.rrs.service;

import cz.ear.rrs.dao.AccountDao;
import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.dao.ReservationDao;
import cz.ear.rrs.dao.RoomDao;
import cz.ear.rrs.dto.ReservationDTO;
import cz.ear.rrs.dto.ReservedEquipmentPOJO;
import cz.ear.rrs.exception.BaseException;
import cz.ear.rrs.exception.EquipmentIsNotAvailable;
import cz.ear.rrs.exception.LowRightsException;
import cz.ear.rrs.exception.RoomIsNotAvailable;
import cz.ear.rrs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationDao dao;

    private final RoomDao roomDao;

    private final AccountDao accountDao;

    private final EquipmentDao equipmentDao;

    @Autowired
    public ReservationService(ReservationDao dao, RoomDao roomDao, AccountDao accountDao, EquipmentDao equipmentDao) {
        this.dao = dao;
        this.roomDao = roomDao;
        this.accountDao = accountDao;
        this.equipmentDao = equipmentDao;
    }

    @Transactional
    public void persist(Reservation r) {
        dao.persist(r);
        r.getReservedEquipmentList().forEach(e -> e.setReservation(r));
    }

    @Transactional
    public Reservation completeReservationEntity(ReservationDTO dto, Reservation r) {
        r.setAccount(accountDao.find(dto.getAccountId()));
        r.setRoom(roomDao.find(dto.getRoomId()));
        ReservedEquipment eq;
        if (dto.getEquipment() != null) {
            for (ReservedEquipmentPOJO reservedEquipmentPOJO : dto.getEquipment()) {
                eq = new ReservedEquipment();
                eq.setEquipment(equipmentDao.find(reservedEquipmentPOJO.getId()));
                eq.setReservedAmount(reservedEquipmentPOJO.getAmount());
                r.addEquipment(eq);
            }
        }
        return r;
    }

    @Transactional
    public boolean validateReservation(Reservation r) {
        boolean room = true;
        boolean rights = true;
        boolean isLocal = true;
        if (r.getRoom() != null) { // room is null in the of case of creating reservation on equipment only
            room = roomIsAvailable(r);
            rights = hasRightOnRoom(r);
            isLocal = isLocalRoom(r);
        }
        boolean equipment = validateEquipment(r);
        //LOG.info("Reservation -  Room: %s, Equipment: %s, Rights: %s, Is local: %s.", room, equipment, rights, isLocal); prepsat jinak
        return room && equipment && rights && isLocal;
    }


    @Transactional
    public boolean roomIsAvailable(Reservation r) {
        List<Reservation> reservations = dao.findAllReservationsForRoom(r.getRoom());
        reservations = getOverlapsingReservations(r, reservations);
        if ((reservations.size() > 0)) {
            throw new RoomIsNotAvailable("Room is not available at this time");
        } else {
            return true;
        }
    }

    public boolean validateEquipment(Reservation r) {
        if (r.getReservedEquipmentList() == null) {
            return true;
        }
        List<Equipment> desiredEquipment = new ArrayList<>();
        r.getReservedEquipmentList().forEach(e -> {
            desiredEquipment.add(e.getEquipment());
        });
        List<Equipment> availableEq = getAvailableEquipment(r);
        availableEq.forEach(e -> {
            if (!hasRightOnEquipment(r, e)) { //check rights
                throw new LowRightsException("User doesn't have rights for this.");
            }
            if (!isLocalEquipment(r, e)) {
                throw new BaseException("This user cannot make reservations in this building");
            }
        });
        return desiredEquipment.size() == availableEq.size();
    }

    @Transactional
    public List<Equipment> getAvailableEquipment(Reservation r) {
        List<Equipment> availableEquipment = new ArrayList<>();
        HashMap<Equipment, Boolean> list = equipmentIsAvailable(r);
        for (Map.Entry<Equipment, Boolean> entry : list.entrySet()) {
            Equipment key = entry.getKey();
            Boolean availability = entry.getValue();
            if (availability) {
                availableEquipment.add(key);
            }
        }
        return availableEquipment;
    }

    @Transactional
    public HashMap<Equipment, Boolean> equipmentIsAvailable(Reservation r) {
        HashMap<Equipment, Boolean> isAvailable = new HashMap<>();
        Integer totalAmount = 0;
        Long sumReservedAmount = 0L;
        Integer availableQuantity;
        HashMap<Equipment, Integer> list = new HashMap<>();
        r.getReservedEquipmentList().forEach(e -> {
            list.put(e.getEquipment(), e.getReservedAmount());
        });
        for (Map.Entry<Equipment, Integer> entry : list.entrySet()) {
            Equipment key = entry.getKey();
            Integer desiredQuantity = entry.getValue();
            isAvailable.put(key, true); //default
            List<Object[]> overlapsingEq = equipmentDao.getOverlapsingEquipment(r, key); // get all overlapping equipment
            if (overlapsingEq.size() > 0) {
                // if there are some overlapping reservations on equipment, then check available quantity
                // totalAmount - sum(reservedAmount) >= reservedAmount of the desired equipment
                for (Object[] record : overlapsingEq) {
                    totalAmount = (Integer) record[0];
                    sumReservedAmount = (Long) record[1];
                }
                availableQuantity = Math.toIntExact(totalAmount - sumReservedAmount);
                if (availableQuantity < desiredQuantity) {
                    isAvailable.put(key, false);
                    throw EquipmentIsNotAvailable.create(key.getName(), availableQuantity);
                }
            }
        }
        return isAvailable;
    }

    private boolean hasRightOnRoom(Reservation r) {
        if (r.getAccount().getRights().ordinal() <= r.getRoom().getRights().ordinal()) {
            return true;
        } else {
            throw new LowRightsException("User doesn't have rights for this.");
        }
    }

    private boolean hasRightOnEquipment(Reservation r, Equipment eq) {
        if (r.getAccount().getRights().ordinal() <= eq.getRights().ordinal()) {
            return true;
        } else {
            throw new LowRightsException("User doesn't have rights for this.");
        }
    }


    private boolean isLocalRoom(Reservation r) {
        if (containsObject(r.getAccount().getBuildings(), r.getRoom().getBuilding())) {
            return true;
        } else {
            throw new BaseException("This user cannot make reservations in this building");
        }
    }

    private boolean isLocalEquipment(Reservation r, Equipment eq) {
        if (containsObject(r.getAccount().getBuildings(), eq.getBuilding())) {
            return true;
        } else {
            throw new BaseException("This user cannot make reservations in this building");
        }
    }

    @Transactional
    public List<Reservation> getOverlapsingReservations(Reservation r, List<Reservation> reservations) {
        List<Reservation> overlappingReserv = new ArrayList<>();
        for (Reservation rsrv : reservations) {
            // Checks for overlapsing of two time intervals
            if (r.getDate().isEqual(rsrv.getDate())) {
                if ((rsrv.getStart_At().equals(r.getStart_At())
                        || rsrv.getEnd_At().equals(r.getEnd_At())
                        || r.getStart_At().isAfter(rsrv.getStart_At()) && r.getStart_At().isBefore(rsrv.getEnd_At())
                        || r.getEnd_At().isAfter(rsrv.getStart_At()) && r.getEnd_At().isBefore(rsrv.getEnd_At()))) {
                    overlappingReserv.add(rsrv);
                }
            }
        }
        return overlappingReserv;
    }

    @Transactional
    public boolean containsObject(List<Building> objects, Building object) {
        for (Building ob : objects) {
            if (ob.getId().equals(object.getId())) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void update(Reservation reservation) {
        Objects.requireNonNull(reservation);
        dao.update(reservation);
    }

    @Transactional
    public List<Reservation> findAll() {
        return dao.findAll();
    }

    @Transactional
    public Reservation find(int id) {
        return dao.find(id);
    }

    @Transactional
    public void remove(Reservation res) {
        Objects.requireNonNull(res);
        dao.remove(res);
    }

    @Transactional
    public List<Reservation> getAllReservationsAtCertainTime(LocalDate date, LocalTime startAt, LocalTime endAt) {
        return dao.findAll()
                .stream()
                .filter(reservation -> reservation.getDate() == date)
                .filter(reservation -> reservation.getStart_At() == startAt)
                .filter(reservation -> reservation.getEnd_At() == endAt)
                .collect(Collectors.toList());
    }
}

