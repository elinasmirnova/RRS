package cz.ear.rrs.dao;

import cz.ear.rrs.model.Equipment;
import cz.ear.rrs.model.Reservation;

import cz.ear.rrs.model.ReservedEquipment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Repository
public class EquipmentDao extends BaseDao<Equipment> {

    public EquipmentDao() {
        super(Equipment.class);
    }

//    public List<Equipment> findAll() {
//        try {
//            return em.createNamedQuery("Equipment.findAll", Equipment.class).getResultList();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

    @Transactional
    public List<ReservedEquipment> findAllReservedEquipment() {
        try {
            return em.createQuery("SELECT r FROM ReservedEquipment AS r", ReservedEquipment.class).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<Object[]> getOverlapsingEquipment(Reservation r, Equipment eq) {
        Query query = em.createNativeQuery("SELECT totalamount, sum(reservedamount) FROM reserved_equipment equip JOIN reservation ro ON equip.reservation_id = ro.id " +
                " JOIN equipment e on equip.equipment_id = e.id" +
                " WHERE (date = ?1) AND (equipment_id = ?4) AND ((start_at = ?2) OR (end_at = ?3) " +
                "OR ((?2 > start_at) AND (?2 < end_at))" +
                "OR (?3 > start_at) AND (?3 < end_at))" +
                "GROUP BY totalamount, reservedamount");
        query.setParameter(1, r.getDate());
        query.setParameter(2, r.getStart_At());
        query.setParameter(3, r.getEnd_At());
        query.setParameter(4, eq.getId());
        List<Object[]> reservedEquipment = query.getResultList();
        return reservedEquipment;

    }




}
