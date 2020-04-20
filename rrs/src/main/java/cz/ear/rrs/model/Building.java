package cz.ear.rrs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Building extends AbstractEntity{
    //    private int id;
    private String address;

    //    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
    @ManyToMany(mappedBy = "buildings", fetch=FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    private List<Equipment> equipment = new ArrayList<>();

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return getAddress().equals(building.getAddress()) &&
                Objects.equals(accounts, building.accounts) &&
                Objects.equals(rooms, building.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), accounts, rooms);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Building building = (Building) o;
//        return id == building.id &&
//                Objects.equals(address, building.address);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, address);
//    }
}
