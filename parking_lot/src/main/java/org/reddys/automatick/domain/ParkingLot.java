package org.reddys.automatick.domain;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static ParkingLot instance;

    private ParkingLot() {}

    private ArrayList<ParkingSlot> slots = new ArrayList<ParkingSlot>();

    public void setLimit(int numberOfSlots) throws InvalidLimitException {
        if (slots.size() > numberOfSlots) {
            ArrayList<ParkingSlot> copyOfSlots = new ArrayList<ParkingSlot>(slots);
            for (int i = numberOfSlots ; i < slots.size(); i++) {
                if (!copyOfSlots.get(numberOfSlots).isFree()) {
                    throw new InvalidLimitException("Vehicles still parked, limiting of slots not allowed");
                }
                copyOfSlots.remove(numberOfSlots);
            }
            if (copyOfSlots.size() == numberOfSlots) slots = copyOfSlots;
        }

        for (int i=slots.size();i<numberOfSlots;i++) slots.add(new ParkingSlot(i));
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
        for (ParkingSlot slot: slots) {
            if (slot.isFree()) {
                return slot;
            }
        }
        throw new ParkingFullException();
    }

    public void leave(int slotNumber) {
        ParkingSlot slot = slots.get(slotNumber);
        slot.setParkedVehicle(null);
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }


    public List<ParkingSlot> colorQuery(final String color) {
        return ListUtils.select(slots, new Predicate <ParkingSlot>() {
            @Override
            public boolean evaluate(ParkingSlot parkingSlot) {
                return !parkingSlot.isFree() && parkingSlot.getParkedVehicle().getColor().equals(color);
            }
        });
    }

    public int registrationNumberVsSlotNumberQuery(final String registrationNumber) {
        for (ParkingSlot slot: slots) {
            if (slot.getParkedVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return slot.getSlotNumber();
            }
        }
        return -1;
    }

}
