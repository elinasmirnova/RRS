package cz.ear.rrs.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReservedEquipmentKey implements Serializable {

    @Column(name = "reservation_id")
    private Integer reservationId;

    @Column(name = "equipment_id")
    private Integer equipmentId;

    public ReservedEquipmentKey(Integer reservationId, Integer equipmentId) {
        this.reservationId = reservationId;
        this.equipmentId = equipmentId;
    }

    public ReservedEquipmentKey() {

    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
}
