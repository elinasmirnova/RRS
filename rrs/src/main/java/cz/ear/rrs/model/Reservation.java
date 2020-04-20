package cz.ear.rrs.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
public class Reservation extends AbstractEntity{
    //    private int id;
    private LocalDate date;
    private LocalTime start_At;
    private LocalTime end_At;

    //    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation",orphanRemoval = true)
    private List<ReservedEquipment> reservedEquipmentList;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    public void addEquipment(ReservedEquipment equipment) {
        Objects.requireNonNull(equipment);
        if (this.reservedEquipmentList == null) {
            this.reservedEquipmentList = new ArrayList<>();
        }
        reservedEquipmentList.add(equipment);
    }

//    public void removeEquipment(Equipment equipment) {
//        Objects.requireNonNull(equipment);
//        final Iterator<ReservedEquipment> iterator = reservedEquipmentList.iterator();
//        while (iterator.hasNext()) {
//            final ReservedEquipment current = iterator.next();
//            if (current.getEquipment().getId().equals(equipment.getId())) {
//                iterator.remove();
//            }
//        }
//    }

    public List<ReservedEquipment> getReservedEquipmentList() {
        return reservedEquipmentList;
    }

    public void setReservedEquipmentList(List<ReservedEquipment> reservedEquipmentList) {
        this.reservedEquipmentList = reservedEquipmentList;
    }

    @Basic
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Basic
    @Column(name = "start_at")
    public LocalTime getStart_At() {
        return start_At;
    }

    public void setStart_At(LocalTime startAt) {
        this.start_At = startAt;
    }

    @Basic
    @Column(name = "end_at")
    public LocalTime getEnd_At() {
        return end_At;
    }

    public void setEnd_At(LocalTime endAt) {
        this.end_At = endAt;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Reservation that = (Reservation) o;
//        return id == that.id &&
//                Objects.equals(date, that.date) &&
//                Objects.equals(startAt, that.startAt) &&
//                Objects.equals(endAt, that.endAt);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, date, startAt, endAt);
//    }
}
