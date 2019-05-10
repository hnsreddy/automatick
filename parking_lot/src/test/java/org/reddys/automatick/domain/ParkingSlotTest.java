package org.reddys.automatick.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingSlotTest {

    @Test
    public void getSlotNumber() {
        ParkingSlot slot = new ParkingSlot(1);
        Assert.assertEquals(1, slot.getSlotNumber());
    }

    @Test
    public void getParkedVehicle() {
        ParkingSlot slot = new ParkingSlot (1);
        Assert.assertNull(slot.getParkedVehicle());
        slot.setParkedVehicle(new Vehicle("test1", "white"));
        Assert.assertEquals("test1", slot.getParkedVehicle().getRegistrationNumber());
    }

    @Test
    public void setParkedVehicle() {
        ParkingSlot slot = new ParkingSlot (1);
        slot.setParkedVehicle(new Vehicle("test5", "white"));
        Assert.assertEquals("test5", slot.getParkedVehicle().getRegistrationNumber());
    }

    @Test
    public void isFree() {
        ParkingSlot slot = new ParkingSlot(2);
        Assert.assertTrue(slot.isFree());
    }

    @Test
    public void isFreeFalseCase() {
        ParkingSlot slot = new ParkingSlot(2);
        slot.setParkedVehicle(new Vehicle("test6", "purple"));
        Assert.assertFalse(slot.isFree());
    }

}