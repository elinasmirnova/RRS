package cz.ear.rrs.service;


import cz.ear.rrs.dao.AccountDao;
import cz.ear.rrs.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;

@Service
public class AccountService {

    private final AccountDao dao;

    @Autowired
    public AccountService(AccountDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(Account acc) {
        Objects.requireNonNull(acc);
        dao.persist(acc);
    }

    @Transactional
    public void update(Account acc) {
        Objects.requireNonNull(acc);
        dao.update(acc);
    }

    @Transactional
    public void remove(Account acc) {
        Objects.requireNonNull(acc);
        dao.remove(acc);
    }

    @Transactional
    public Account findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Transactional
    public Account findById(int id) {
        return dao.find(id);
    }

    @Transactional
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Transactional
    public boolean exists(String username) {
        return dao.findByUsername(username) != null;
    }

}
