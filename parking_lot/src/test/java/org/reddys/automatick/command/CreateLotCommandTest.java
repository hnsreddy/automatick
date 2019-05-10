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

public class CreateLotCommandTest {

    @BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }

    @Test
    public void executeCLICommandNoArguments() throws IOException {
        CreateLotCommand command = new CreateLotCommand();
        String args[] = {};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - create_parking_lot command requires number of slots to be specified\n", writer.toString());
    }

    @Test
    public void executeCLICommandInvalidArgument() throws IOException {
        CreateLotCommand command = new CreateLotCommand();
        String args[] = {"s"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid argument passed, cannot create a parking lot with specified arguments. Try and be a little more sensible\n", writer.toString());
    }

    @Test
    public void executeCLICommandParkingLotAlreadyCreated() throws IOException {
        CreateLotCommand command = new CreateLotCommand();
        String args[] = {"4"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Parking lot already created \n", writer.toString());
    }

    @Test
    public void executeCLICommand() throws IOException, IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        CreateLotCommand command = new CreateLotCommand();
        String args[] = {"4"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Created a parking lot with 4 slots\n", writer.toString());
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

}