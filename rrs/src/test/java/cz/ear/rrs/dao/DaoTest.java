package cz.ear.rrs.dao;


import cz.ear.rrs.RrsApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// DataJpaTest does not load all the application beans, it starts only persistence-related stuff
@DataJpaTest
// Exclude SystemInitializer from the startup, we don't want the admin account here
@ComponentScan(basePackageClasses = RrsApplication.class)
public class DaoTest {

    @Autowired
    protected AccountDao accountDao;

    @Autowired
    protected BuildingDao buildingDao;

    @Autowired
    protected EquipmentDao equipmentDao;

    @Autowired
    protected ReservationDao reservationDao;

    @Autowired
    protected RoomDao roomDao;


    /**
     * This test checks if the spring application contexts contains all the DAO beans from the cz.cvut.kbss.ear.eshop.dao  package
     */
    @Test
    public void testRepositoriesInApplicationContext(){
        Assert.assertNotNull(accountDao);
        Assert.assertNotNull(buildingDao);
        Assert.assertNotNull(equipmentDao);
        Assert.assertNotNull(reservationDao);
        Assert.assertNotNull(roomDao);
    }
}
