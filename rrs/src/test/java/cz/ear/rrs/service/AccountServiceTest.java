package cz.ear.rrs.service;

import cz.ear.rrs.environment.Generator;
import cz.ear.rrs.model.Account;
import cz.ear.rrs.model.Equipment;
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
public class AccountServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountService service;

    private Account acc;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersist() {
        List<Account> accList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            acc = Generator.generateUser();
            accList.add(acc);
            service.persist(acc);
        }

        final Account accResult = em.find(Account.class, accList.get(4).getId());

        Assert.assertEquals(accResult.getId(), acc.getId());

    }


    @Test
    public void testRemove() {
        List<Account> accList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            acc = Generator.generateUser();
            accList.add(acc);
            service.persist(acc);
        }

        service.remove(acc);

        Assert.assertNull(em.find(Account.class, acc.getId()));
    }
}