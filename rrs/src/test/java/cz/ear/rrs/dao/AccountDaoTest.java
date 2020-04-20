package cz.ear.rrs.dao;

import cz.ear.rrs.RrsApplication;
import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.model.Account;
import cz.ear.rrs.service.SystemInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

// For explanatory comments, see ProductDaoTest
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = RrsApplication.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SystemInitializer.class)})
public class AccountDaoTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountDao sut;

    @Test
    public void findByUsernameReturnsPersonWithMatchingUsername() {
        final Account user = Generator.generateUser();
        em.persist(user);

        final Account result = sut.findByUsername(user.getUsername());
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void findByUsernameReturnsNullForUnknownUsername() {
        assertNull(sut.findByUsername("unknownUsername"));
    }
}
