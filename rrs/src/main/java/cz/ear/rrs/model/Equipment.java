package cz.ear.rrs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Equipment extends Reservable{
    //    private int id;
//    private String location;
    private int totalAmount;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH} , fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment", orphanRemoval = true)
    private List<ReservedEquipment> reservedEquipment;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    //    private String name;

//    @Enumerated(EnumType.STRING)
//    private Rights rights;

//    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    @Basic
//    @Column(name = "location")
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }

    @Basic
    @Column(name = "totalamount")
    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

//    @Basic
//    @Column(name = "name")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Basic
//    @Column(name = "rights")
//    public Rights getRights() {
//        return rights;
//    }
//
//    public void setRights(Rights rights) {
//        this.rights = rights;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Equipment equipment = (Equipment) o;
//        return id == equipment.id &&
//                totalAmount == equipment.totalAmount &&
//                Objects.equals(location, equipment.location) &&
//                Objects.equals(name, equipment.name) &&
//                Objects.equals(rights, equipment.rights);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, location, totalAmount, name, rights);
//    }
}
