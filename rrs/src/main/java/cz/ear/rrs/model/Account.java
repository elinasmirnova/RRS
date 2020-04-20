package cz.ear.rrs.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Account.findByUsername", query = "SELECT u FROM Account u WHERE u.username = :username")
})
@Entity
public class Account extends AbstractEntity{

    private String firstname;
    private String password;
    private String lastname;
    private String username;


    @Enumerated(EnumType.STRING)
    private Rights rights;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_building",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private List<Building> buildings;


    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void addBuilding(Building building) {
        Objects.requireNonNull(building);
        if (buildings == null) {
            this.buildings = new ArrayList<>();
        }
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        Objects.requireNonNull(building);
        final Iterator<Building> it = buildings.iterator();
        while (it.hasNext()) {
            final Building current = it.next();
            if (current.getId().equals(building.getId())) {
                it.remove();
                break;
            }
        }
    }

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "rights")
    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public void erasePassword() {
        this.password = null;
    }

    public String getName() {
        return getFirstname() + " " + getLastname();
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return id == account.id &&
//                Objects.equals(firstname, account.firstname) &&
//                Objects.equals(password, account.password) &&
//                Objects.equals(lastname, account.lastname) &&
//                Objects.equals(username, account.username) &&
//                Objects.equals(rights, account.rights);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstname, password, lastname, username, rights);
//    }
}
