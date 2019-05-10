package org.reddys.automatick.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;

import java.lang.reflect.Field;
import java.util.List;

public class ParkingLotTest {

    @Before
    public void resetLotInstance() throws IllegalAccessException, NoSuchFieldException{
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void setLimit() throws InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        Assert.assertEquals(4, ParkingLot.getInstance().getLimit());
    }

    @Test
    public void setLimitReduceValid() throws ParkingFullException, InvalidLimitException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().setLimit(3);
        Assert.assertEquals(3, ParkingLot.getInstance().getLimit());
    }

    @Test (expected = InvalidLimitException.class)
    public void setLimitReduceInValid() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(2);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().setLimit(1);
    }

    @Test (expected = ParkingFullException.class)
    public void parkWhenLimitNotSet () throws ParkingFullException{
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
    }

    @Test
    public void parkTheRightWay () throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(2);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
    }

    @Test
    public void leaveHappyPath() throws InvalidLimitException, ParkingFullException{
        ParkingLot.getInstance().setLimit(2);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().leave(0);
        int slotNumber = ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        Assert.assertEquals(0, slotNumber);
    }

    @Test
    public void colorQuery () throws ParkingFullException, InvalidLimitException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "white"));
        ParkingLot.getInstance().park(new Vehicle("test4", "blue"));

        List <ParkingSlot> slots =  ParkingLot.getInstance().colorQuery("white");
        Assert.assertEquals("test1",slots.get(0).getParkedVehicle().getRegistrationNumber());
        Assert.assertEquals("test3",slots.get(1).getParkedVehicle().getRegistrationNumber());
    }

    @Test
    public void colorQueryNoResult () throws ParkingFullException, InvalidLimitException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "white"));
        ParkingLot.getInstance().park(new Vehicle("test4", "blue"));

        List <ParkingSlot> slots =  ParkingLot.getInstance().colorQuery("purple");
        Assert.assertEquals(0, slots.size());
    }

    @Test
    public void registrationNumberVsSlotNumberQuery () throws InvalidLimitException, ParkingFullException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "white"));
        ParkingLot.getInstance().park(new Vehicle("test4", "blue"));
        int slotNumber = ParkingLot.getInstance().registrationNumberVsSlotNumberQuery("test3");
        Assert.assertEquals(2, slotNumber);
    }

    @Test
    public void registrationNumberVsSlotNumberQueryNoResult () throws InvalidLimitException, ParkingFullException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "white"));
        ParkingLot.getInstance().park(new Vehicle("test4", "blue"));
        int slotNumber = ParkingLot.getInstance().registrationNumberVsSlotNumberQuery("test5");
        Assert.assertEquals(-1, slotNumber);
    }
}
