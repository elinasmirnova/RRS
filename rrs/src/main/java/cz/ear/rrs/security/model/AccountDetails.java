package cz.ear.rrs.security.model;

import cz.ear.rrs.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class AccountDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public AccountDetails(Account user) {
        this.userName = user.getUsername();
        this.active = true;
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRights().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

//    public AccountDetails(Account user) {
//        Objects.requireNonNull(user);
//        this.user = user;
//        this.authorities = new HashSet<>();
//        addUserRole();
//    }
//
//    public AccountDetails(Account user, Collection<GrantedAuthority> authorities) {
//        Objects.requireNonNull(user);
//        Objects.requireNonNull(authorities);
//        this.user = user;
//        this.authorities = new HashSet<>();
//        addUserRole();
//        this.authorities.addAll(authorities);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

//    private void addUserRole() {
//        authorities.add(new SimpleGrantedAuthority(user.getRights().toString()));
//    }
//    private void addUserRole() {
//    authorities.add(new SimpleGrantedAuthority("ADMIN"));
//}


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.unmodifiableCollection(authorities);
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> a = new HashSet<>();
//        a.add(new SimpleGrantedAuthority("ADMIN"));
//        return Collections.unmodifiableCollection(a);
//    }

//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public Account getUser() {
//        return user;
//    }
//
//    public void eraseCredentials() {
//        user.erasePassword();
//    }
}
