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

public class LeaveCommandTest {

    @BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }

    @Test
    public void executeCLICommandInvalid() throws IOException {
        LeaveCommand command = new LeaveCommand();
        String args[] = {};
        StringWriter writer =  new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - leave command requires slot number to be specified", writer.toString());
    }

    @Test
    public void executeCLICommand() throws IOException {
        LeaveCommand command = new LeaveCommand();
        String args[] = {"2"};
        StringWriter writer =  new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Slot number 2 is free", writer.toString());
    }


    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}