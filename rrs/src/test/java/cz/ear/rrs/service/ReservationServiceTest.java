package cz.ear.rrs.service;

import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.environment.GeneratorUtils;
import cz.ear.rrs.exception.RoomIsNotAvailable;
import cz.ear.rrs.model.Account;
import cz.ear.rrs.model.Reservation;
import cz.ear.rrs.model.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationService service;

//    @Autowired
//    private Room

    private Reservation reservation;

    @Before
    public void setUp() {
        Room room = Generator.generateRoom();
        for (int i = 0; i < 5; i++) {
            reservation = Generator.generateReservation();
            reservation.setRoom(room);
        }

    }

    @Test
    public void roomIsAvailableTest() {

    }

//    @Test
//    public void createReservation() {
//        Account user = Generator.generateUser();
//        Room room = Generator.generateRoom();
//
//        em.persist(user);
//        em.persist(room);
//
//        LocalTime timeFrom = GeneratorUtils.createRandomTimeFrom();
//
//        service.createReservation(reservation, room, user, GeneratorUtils.createRandomDate(), timeFrom, GeneratorUtils.createRandomTimeTo(timeFrom));
//
//        final Reservation reservationResult = em.find(Reservation.class, reservation.getId());
//        final Account userResult = em.find(Account.class, user.getId());
//        final Room roomResult = em.find(Room.class, room.getId());
//
//        Assert.assertEquals(reservationResult.getId(), reservation.getId());
//        Assert.assertEquals(userResult.getId(), user.getId());
//        Assert.assertEquals(roomResult.getId(), room.getId());
//    }
//
//    @Test
//    public void createReservationWithEquipment() {
//        Account user = Generator.generateUser();
//        Room room = Generator.generateRoom();
//
//        em.persist(user);
//        em.persist(room);
//
//        LocalTime timeFrom = GeneratorUtils.createRandomTimeFrom();
//
//        service.createReservation(reservation, room, user, GeneratorUtils.createRandomDate(), timeFrom, GeneratorUtils.createRandomTimeTo(timeFrom));
//
//        final Reservation reservationResult = em.find(Reservation.class, reservation.getId());
//        final Account userResult = em.find(Account.class, user.getId());
//        final Room roomResult = em.find(Room.class, room.getId());
//
//        Assert.assertEquals(reservationResult.getId(), reservation.getId());
//        Assert.assertEquals(userResult.getId(), user.getId());
//        Assert.assertEquals(roomResult.getId(), room.getId());
//    }
//
//
//    @Test(expected = RoomIsNotAvailable.class)
//    public void createReservationThrowsRoomIsNotAvailableException() throws Exception {
//        Account user = Generator.generateUser();
//        Room room = Generator.generateRoom();
//
//        LocalDate date = LocalDate.of(2020, Month.JANUARY, 15);
//        LocalTime timeFrom = LocalTime.of(10, 0, 0);
//        LocalTime timeTo = LocalTime.of(12, 0, 0);
//
//        service.createReservation(reservation, room, user, date, timeFrom, timeTo);
//
//        Reservation overlappingReservation = Generator.generateReservation();
//
//        timeTo = LocalTime.of(13, 0, 0);
//
//        service.createReservation(overlappingReservation, room, user, date, timeFrom, timeTo);
//
//    }




}
