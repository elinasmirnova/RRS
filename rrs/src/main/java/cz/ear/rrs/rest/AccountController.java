package cz.ear.rrs.rest;

import cz.ear.rrs.model.Account;
import cz.ear.rrs.rest.util.RestUtils;
import cz.ear.rrs.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    private final AccountService as;

    @Autowired
    public AccountController(AccountService as) {
        this.as = as;
    }


    @GetMapping("/me")
    public Account getCurrentUser(Authentication authentication) {
        return as.findByUsername(authentication.getName());
    }

    @PreAuthorize("hasAuthority('0')")
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return as.findById(id);
    }

    //    @PreAuthorize("hasAuthority('0')")
    @GetMapping
    public List<Account> getAccounts() {
        return as.findAll();
    }

    @PreAuthorize("hasAuthority('0')")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> createAccount(@RequestBody Account a) {
        as.persist(a);
        LOG.debug("User {} created", a);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/me");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('0')")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccount(@RequestBody Account a) {
        as.update(a);
        LOG.debug("User updated");
    }

    @PreAuthorize("hasAuthority('0')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable int id) {
        as.remove(as.findById(id));
        LOG.debug("User deleted");
    }
}
