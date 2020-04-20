package cz.ear.rrs.service;


import cz.ear.rrs.dao.RoomDao;
import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.model.Room;
import org.junit.Assert;
import org.junit.Test;
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
public class RoomServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoomDao dao;

    @Autowired
    private RoomService service;

    private Room room;

    @Test
    public void testFindAllRooms() {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            room = Generator.generateRoom();
            rooms.add(room);
            service.persist(room);
        }

        final List<Room> roomsResult = dao.findAll();

        Assert.assertEquals(roomsResult.get(0).getId(), rooms.get(0).getId());
    }


}
