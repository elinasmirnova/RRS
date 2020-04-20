package cz.ear.rrs.service;

import cz.ear.rrs.dao.ReservationDao;
import cz.ear.rrs.dao.RoomDao;
import cz.ear.rrs.model.Reservation;
import cz.ear.rrs.model.Rights;
import cz.ear.rrs.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoomService {

    private final RoomDao roomDao;
    private final ReservationDao reservationDao;


    @Autowired
    public RoomService(RoomDao roomDao, ReservationDao reservationDao) {
        this.roomDao = roomDao;
        this.reservationDao = reservationDao;
    }

    @Transactional
    public void persist(Room room) {
        roomDao.persist(room);
    }

    @Transactional
    public void update(Room room) {
        roomDao.update(room);
    }

    @Transactional
    public void changeRights(Room room, Rights rights) {
        room.setRights(rights);
        roomDao.update(room);
    }

//    public void getAvailableRooms(LocalDate date, LocalTime from, LocalTime to) {
//        roomDao.getAvailableRooms(date, from, to);
//    }

    @Transactional
    public HashMap<String, List> getRoomAvailability(Room room, LocalDate date) {
        HashMap<String, List> schedule = new HashMap<>();

        for (Reservation r : reservationDao.findAllReservationsForRoom(room)) {
            if (r.getDate().equals(date)) {
                ArrayList fromTo = new ArrayList();
                fromTo.add(r.getStart_At());
                fromTo.add(r.getEnd_At());
                schedule.put(r.getAccount().getName(), fromTo);
            }
        }
        return schedule;
    }

    @Transactional
    public HashMap<String, Integer> getRoomUsage() {
        HashMap<String, Integer> usage = new HashMap<>();
        List<Room> allRooms = roomDao.findAll();
        for (Room room : allRooms) {
            usage.put(room.getLocation(), reservationDao.findAllReservationsForRoom(room).size());
        }
        return usage;
    }

    @Transactional
    public void remove(Room room) {
        roomDao.remove(room);
    }

    @Transactional
    public Room find(int id) {
        return roomDao.find(id);
    }

    @Transactional
    public List<Room> findAll() {
        return roomDao.findAll();
    }

}
