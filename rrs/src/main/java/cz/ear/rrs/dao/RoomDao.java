package cz.ear.rrs.dao;

import cz.ear.rrs.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class RoomDao extends BaseDao<Room> {
    public RoomDao() {
        super(Room.class);
    }

//    public List<Room> findAll() {
//        try {
//            return em.createNamedQuery("Room.findAll", Room.class).getResultList();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

//    public List<Room> getAvailableRooms(LocalDate date, LocalTime from, LocalTime to) {
//        List<Room> allRooms = findAll();
//        Query query = em.createQuery("SELECT a FROM Reservation res JOIN Room ro ON res.room_id = ro.id WHERE (res.date = :Date) AND (res.start_At = :From)  AND (res.end_At = :To)");
//        query.setParameter("Date", date);
//        query.setParameter("From", from);
//        query.setParameter("To", to);
//        List<Room> reservedRooms = query.getResultList();
//        allRooms.removeAll(reservedRooms);
//        return allRooms;
//    }
}
