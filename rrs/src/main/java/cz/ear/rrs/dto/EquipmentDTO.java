package cz.ear.rrs.dto;

import javax.validation.constraints.NotNull;

public class EquipmentDTO {

    @NotNull
    private String name;

    @NotNull
    private String location;
    @NotNull
    private int totalAmount;
}
