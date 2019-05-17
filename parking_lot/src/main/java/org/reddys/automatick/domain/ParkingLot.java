package org.reddys.automatick.domain;

import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.InvalidSlotNumberException;
import org.reddys.automatick.exception.ParkingFullException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingLot {

    private static ParkingLot instance;

    private ParkingLot() {}

    private ArrayList<ParkingSlot> slots = new ArrayList<ParkingSlot>();

    public void setLimit(int numberOfSlots)  {
        if (slots.size() > 0) {
            throw new InvalidLimitException("Limit once set cannot be changed");
        }
        
        for (int i=0;i<numberOfSlots;i++) slots.add(new ParkingSlot(i));
    }

    public int getLimit() {
        return slots.size();
    }

    public int park(Vehicle vehicle) throws ParkingFullException {
        ParkingSlot slot = retrieveFirstFreeSlot();
        slot.setParkedVehicle(vehicle);
        return slot.getSlotNumber();
    }

    private ParkingSlot retrieveFirstFreeSlot() throws ParkingFullException {
        Optional<ParkingSlot> answer = slots.stream().filter(slot -> slot.isFree()).findFirst();
        if (answer.isPresent()) {
            return answer.get();
        }
        throw new ParkingFullException("Sorry, parking lot is full");
    }

    public void leave(int slotNumber) throws InvalidSlotNumberException {
        if (slotNumber < slots.size()) {
            ParkingSlot slot = slots.get(slotNumber);
            slot.setParkedVehicle(null);
        } else {
            throw new InvalidSlotNumberException("Slot number specified does not exist");
        }
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }


    public List<ParkingSlot> colorQuery(final String color) {
        return slots.stream().filter(slot -> !slot.isFree() && slot.getParkedVehicle().getColor().equals(color)).collect(Collectors.toList());
    }

    public int registrationNumberVsSlotNumberQuery(final String registrationNumber) {
        Optional<ParkingSlot> answer = slots.stream().filter(slot -> slot.getParkedVehicle().getRegistrationNumber().equals(registrationNumber)).findAny();
        if (answer.isPresent()) {
            return answer.get().getSlotNumber();
        }
        return -1;
    }

}
