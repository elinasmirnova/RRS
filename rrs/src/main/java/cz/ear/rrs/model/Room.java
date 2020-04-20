package cz.ear.rrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
public class Room extends Reservable{
    //    private int id;
//    private String location;
    private int capacity;
//    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

//    public List<Reservation> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(List<Reservation> reservations) {
//        this.reservations = reservations;
//    }
//
//
//    public void addReservation(Reservation reservation) {
//        Objects.requireNonNull(reservation);
//        if (reservations == null) {
//            this.reservations = new ArrayList<>();
//        }
//        reservations.add(reservation);
//    }
//
//    public void removeReservation(Reservation reservation) {
//        Objects.requireNonNull(reservation);
//        final Iterator<Reservation> it = reservations.iterator();
//        while (it.hasNext()) {
//            final Reservation current = it.next();
//            if (current.getId().equals(reservation.getId())) {
//                it.remove();
//                break;
//            }
//        }
//    }


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
//
//    @Basic
//    @Column(name = "location")
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }

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
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Room room = (Room) o;
//        return id == room.id &&
//                capacity == room.capacity &&
//                Objects.equals(location, room.location) &&
//                Objects.equals(name, room.name) &&
//                Objects.equals(rights, room.rights);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, location, capacity, name, rights);
//    }
}
