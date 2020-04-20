package cz.ear.rrs.dto;

import com.fasterxml.jackson.annotation.*;
import cz.ear.rrs.dao.AccountDao;
import cz.ear.rrs.dao.BuildingDao;
import cz.ear.rrs.dao.EquipmentDao;
import cz.ear.rrs.dao.RoomDao;
import cz.ear.rrs.model.Reservation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationDTO {

    @NotNull
    private int accountId;

    private int roomId;

    @JsonProperty("equipment")
    private List<ReservedEquipmentPOJO> equipment;

    @NotNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private LocalTime start_At;

    @NotNull
    private LocalTime end_At;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<ReservedEquipmentPOJO> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<ReservedEquipmentPOJO> equipment) {
        this.equipment = equipment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart_At() {
        return start_At;
    }

    public void setStart_At(LocalTime start_At) {
        this.start_At = start_At;
    }

    public LocalTime getEnd_At() {
        return end_At;
    }

    public void setEnd_At(LocalTime end_At) {
        this.end_At = end_At;
    }


    public Reservation toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Reservation reservation = modelMapper.map(this, Reservation.class);
        return reservation;
    }
}
