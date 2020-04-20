package cz.ear.rrs.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reserved_equipment")
public class ReservedEquipment {

//    @Id
//    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "reservation_id", nullable = false)
//    private Reservation reservation;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "equipment_id")
//    private Equipment equipment;

    @EmbeddedId
    private ReservedEquipmentKey key;

    @ManyToOne
    @MapsId("reservationId")
    @JoinColumn(columnDefinition = "reservation_id")
    Reservation reservation;

    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(columnDefinition = "equipment_id")
    Equipment equipment;

    private int reservedAmount;

    public ReservedEquipment(Equipment equipment, Reservation reservation, int reservedAmount) {
        this.equipment = equipment;
        this.reservation = reservation;
        this.reservedAmount = reservedAmount;
    }

    public ReservedEquipment() {

    }

    @Basic
    @Column(name = "reservedamount")
    public int getReservedAmount() {
        return reservedAmount;
    }

    public void setReservedAmount(int reservedAmount) {
        this.reservedAmount = reservedAmount;
    }

//    public Reservation getReservation() {
//        return reservation;
//    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
