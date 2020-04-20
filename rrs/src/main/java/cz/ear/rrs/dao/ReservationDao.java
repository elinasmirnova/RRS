package cz.ear.rrs.dao;

import cz.ear.rrs.model.Reservation;
import cz.ear.rrs.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao extends BaseDao<Reservation> {

    protected ReservationDao() {
        super(Reservation.class);
    }

    public List<Reservation> findAllReservationsForRoom(Room room) {
//        List<Reservation> reservations = findAll();
//        for (Reservation rsrv : reservations) {
//            if (!rsrv.getRoom().getId().equals(room.getId())) {
//                reservations.remove(rsrv);
//            }
//        }
//        return reservations;
        List<Reservation> reservations = em.createQuery(
                "SELECT r FROM Reservation AS r WHERE (r.room.id = :roomId)", Reservation.class).setParameter("roomId", room.getId()).getResultList();
        return reservations;
    }
}
