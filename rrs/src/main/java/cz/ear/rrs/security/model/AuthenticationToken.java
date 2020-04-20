package cz.ear.rrs.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken implements Principal {

    private AccountDetails accountDetails;

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, AccountDetails accountDetails) {
        super(authorities);
        this.accountDetails = accountDetails;
        super.setAuthenticated(true);
        super.setDetails(accountDetails);
    }

    @Override
    public String getCredentials() {
        return accountDetails.getPassword();
    }

    @Override
    public AccountDetails getPrincipal() {
        return accountDetails;
    }
}
