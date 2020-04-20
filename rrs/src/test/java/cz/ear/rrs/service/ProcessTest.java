package cz.ear.rrs.service;

import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.environment.GeneratorUtils;
import cz.ear.rrs.model.*;
import cz.ear.rrs.service.AccountService;
import cz.ear.rrs.service.EquipmentService;
import cz.ear.rrs.service.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;

@EntityScan("cz.ear.rrs.model")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProcessTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ReservationService reservationService;


    @Autowired
    private AccountService accountService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BuildingService buildingService;

    private Account user;

    private Reservation reservation;

    private Room room;

    private Building building;

    private int rid;

    @Before
    public void setUp() {
        user = Generator.generateUser();
        reservation = Generator.generateReservation();
        room = Generator.generateRoom();
        building = Generator.generateBuilding();
    }

//    @Test
//    public void createUserAndReservation() {
//
//        accountService.persist(user);
//        roomService.persist(room);
//
//        LocalTime timeFrom = GeneratorUtils.createRandomTimeFrom();
//
//        reservationService.createReservation(reservation, room, user, GeneratorUtils.createRandomDate(), timeFrom, GeneratorUtils.createRandomTimeTo(timeFrom));
//
//        final Account userResult = em.find(Account.class, user.getId());
//        final Room roomResult = em.find(Room.class, room.getId());
//        final Reservation reservationResult = em.find(Reservation.class, reservation.getId());
//
//
//        Assert.assertEquals(reservationResult.getId(), reservation.getId());
//        Assert.assertEquals(userResult.getId(), user.getId());
//        Assert.assertEquals(roomResult.getId(), room.getId());
//
//
//        //reservationService.remove(res);
//
//        Assert.assertNull(em.find(Reservation.class, reservation.getId()));
//
//
//    }

    @Test
    public void createUserAndReservation2() {

        accountService.persist(user);
        roomService.persist(room);
        buildingService.persist(building);
        room.setBuilding(building);
        user.addBuilding(building);
        reservation.setAccount(user);

        LocalTime timeFrom = GeneratorUtils.createRandomTimeFrom();

        reservationService.remove(reservation);

        Assert.assertNull(em.find(Reservation.class, reservation.getId()));


    }

//    @Test
//    public void testEquipment


}
