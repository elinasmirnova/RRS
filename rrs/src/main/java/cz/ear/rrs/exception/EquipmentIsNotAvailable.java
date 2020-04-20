package cz.ear.rrs.exception;

public class EquipmentIsNotAvailable extends EarException {
    public EquipmentIsNotAvailable(String message) {
        super(message);
    }
    public static EquipmentIsNotAvailable create(String equipment, Integer availableAmount) {
        return new EquipmentIsNotAvailable(equipment + "in required quantity is not available at this time. You can reserve only "  +  availableAmount);
    }
}
