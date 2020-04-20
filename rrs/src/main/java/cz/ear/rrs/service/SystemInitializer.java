package cz.ear.rrs.service;


import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class SystemInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(SystemInitializer.class);

    /**
     * Default admin username
     */
    private static final String ADMIN_USERNAME = "sysAdmin";

    private final AccountService accountService;


    private final PlatformTransactionManager txManager;

    @Autowired
    public SystemInitializer(AccountService accountService,
                             PlatformTransactionManager txManager) {
        this.accountService = accountService;
        this.txManager = txManager;
    }

    @PostConstruct
    private void initSystem() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        txTemplate.execute((status) -> {
            generateAdmin();
//            equipmentService.getAvailableEquipment( LocalDate.of(2017, Month.MAY, 15), LocalTime.of(4, 30, 45), LocalTime.of(4, 30, 45) );
            return null;
        });
    }

    /**
     * Generates an admin account if it does not already exist.
     */
    private void generateAdmin() {
        if (accountService.exists(ADMIN_USERNAME)) {
            return;
        }
        final Account admin = new Account();
        admin.setUsername(ADMIN_USERNAME);
        admin.setFirstname("System");
        admin.setLastname("Admin");
        admin.setPassword("adm1n");
        admin.setRights(Rights.ADMIN);
        LOG.info("Generated admin user with credentials " + admin.getUsername() + "/" + admin.getPassword());
        accountService.persist(admin);

//        final Room room = new Room();
//        final Reservation reservation = new Reservation();
//        final Building building = new Building();
//        building.setAddress("Blahblah");
//        buildingService.persist(building);
//        room.setCapacity(10);
//        room.setRights(Rights.ADMIN);
//        room.setName("blah");
//        room.setLocation("blah");
//        room.setBuilding(building);
//        roomService.persist(room);
//        reservation.setAccount(admin);
//        reservation.setRoom(room);
//        reservation.setDate(LocalDate.of(2020, 2, 20));
//        reservation.setStart_At(LocalTime.of(12, 0, 0));
//        reservation.setEnd_At(LocalTime.of(13, 0, 0));
//        reservationService.persist(reservation);
//        System.out.println(equipmentDao.getOverlapsingEquipment(reservation));

    }


}
