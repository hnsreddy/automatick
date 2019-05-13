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

public class ParkCommandTest {

    /*@BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
    }*/

    @Test
    public void executeCLICommandNoArguments() throws IOException {
        ParkCommand command = new ParkCommand();
        String[] args = {};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order", writer.toString());
    }

    @Test
    public void executeCLICommandOneParameter() throws IOException {
        ParkCommand command = new ParkCommand();
        String[] args = {"test1"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order", writer.toString());
    }

    @Test
    public void executeCLICommandParkingFull() throws IOException, ParkingFullException, InvalidLimitException, IllegalAccessException, NoSuchFieldException  {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        ParkingLot.getInstance().setLimit(3);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));

        ParkCommand command = new ParkCommand();
        String[] args = {"test1", "white"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Sorry, parking lot is full", writer.toString());
    }

    @Test
    public void executeCLICommand() throws IOException, IllegalAccessException, NoSuchFieldException, InvalidLimitException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        ParkingLot.getInstance().setLimit(3);

        ParkCommand command = new ParkCommand();
        String[] args = {"test1", "white"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Allocated slot number:0", writer.toString());
    }


    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}