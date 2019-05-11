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

public class RegistrationNumberVsSlotNumberQueryCommandTest {

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
        RegistrationNumberVsSlotNumberQueryCommand command = new RegistrationNumberVsSlotNumberQueryCommand();
        String args[] = {};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - slot_number_for_registration_number command requires the registration number to be specified", writer.toString());
    }

    @Test
    public void executeCLICommandValidRegistrationNumber() throws IOException {
        RegistrationNumberVsSlotNumberQueryCommand command = new RegistrationNumberVsSlotNumberQueryCommand();
        String args[] = {"test3"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("2\n", writer.toString());
    }

    @Test
    public void executeCLICommandNonExistentRegistrationNumber() throws IOException {
        RegistrationNumberVsSlotNumberQueryCommand command = new RegistrationNumberVsSlotNumberQueryCommand();
        String args[] = {"test8"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Not found \n", writer.toString());
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}