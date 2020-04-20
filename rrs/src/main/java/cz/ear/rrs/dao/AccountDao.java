package cz.ear.rrs.dao;

import cz.ear.rrs.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class AccountDao extends BaseDao<Account> {

    public AccountDao() {
        super(Account.class);
    }

    public Account findByUsername(String username) {
        try {
            return em.createNamedQuery("Account.findByUsername", Account.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
