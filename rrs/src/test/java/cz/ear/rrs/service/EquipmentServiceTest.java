package cz.ear.rrs.service;

import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.model.Equipment;
import cz.ear.rrs.model.Reservation;
import cz.ear.rrs.model.Room;
import cz.ear.rrs.service.EquipmentService;
import cz.ear.rrs.service.ReservationService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EquipmentServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EquipmentService service;

    private Equipment equipment;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersist() {
        List<Equipment> equipmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            equipment = Generator.generateEquipment();
            equipmentList.add(equipment);
            service.persist(equipment);
        }

        final Equipment equipmentResult = em.find(Equipment.class, equipmentList.get(4).getId());

        Assert.assertEquals(equipmentResult.getId(), equipment.getId());

    }


    @Test
    public void testRemove() {
        List<Equipment> equipmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            equipment = Generator.generateEquipment();
            equipmentList.add(equipment);
            service.persist(equipment);
        }

        service.remove(equipment);

        Assert.assertNull(em.find(Equipment.class, equipment.getId()));
    }



}
