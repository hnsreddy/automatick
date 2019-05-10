package org.reddys.automatick.command;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ColorVsSlotNumberQueryCommandTest {

    @BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }


    @Test
    public void executeCLICommandOneResult() throws IOException {
        ColorVsSlotNumberQueryCommand command = new ColorVsSlotNumberQueryCommand();
        String args[] = {"red"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("1", writer.toString());
    }

    @Test
    public void executeCLICommandMultipleResults() throws IOException {
        ColorVsSlotNumberQueryCommand command = new ColorVsSlotNumberQueryCommand();
        String args[] = {"white"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("0,3", writer.toString());
    }

    @Test
    public void executeCLICommandInvalid() throws IOException {
        ColorVsSlotNumberQueryCommand command = new ColorVsSlotNumberQueryCommand();
        String args[] = {};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - slot_numbers_for_cars_with_colour command requires the color to be specified", writer.toString());
    }

    @Test
    public void executeCLICommandNoResult() throws IOException {
        ColorVsSlotNumberQueryCommand command = new ColorVsSlotNumberQueryCommand();
        String args[] = {"brown"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("No vehicles found", writer.toString());
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}