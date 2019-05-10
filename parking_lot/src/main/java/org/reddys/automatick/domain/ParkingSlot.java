package org.reddys.automatick.domain;

public class ParkingSlot {

    private int slotNumber;

    private Vehicle parkedVehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

}
