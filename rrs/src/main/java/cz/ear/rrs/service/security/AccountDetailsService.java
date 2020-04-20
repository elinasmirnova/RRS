package cz.ear.rrs.service.security;

import cz.ear.rrs.dao.AccountDao;
import cz.ear.rrs.model.Account;
import cz.ear.rrs.security.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountDao accountDao;

    @Autowired
    public AccountDetailsService(AccountDao userDao) {
        this.accountDao = userDao;
    }

    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account user = accountDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        return new AccountDetails(user);
    }
}
