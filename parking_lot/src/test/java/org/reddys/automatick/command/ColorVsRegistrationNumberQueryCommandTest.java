package org.reddys.automatick.command;

import org.junit.*;
import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ColorVsRegistrationNumberQueryCommandTest {

    @BeforeClass
    public static void beforeAllTest() throws ParkingFullException, InvalidLimitException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }
    @Test
    public void executeCLICommand() throws IOException {
        ColorVsRegistrationNumberQueryCommand command = new ColorVsRegistrationNumberQueryCommand();
        String args[] = {"white"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("test1,test4\n", writer.toString());
    }

    @Test
    public void executeCLICommandNoResults() throws IOException {
        ColorVsRegistrationNumberQueryCommand command = new ColorVsRegistrationNumberQueryCommand();
        String args[] = {"brown"};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("No vehicles found \n", writer.toString());
    }

    @Test
    public void executeCLICommandInvalid() throws IOException {
        ColorVsRegistrationNumberQueryCommand command = new ColorVsRegistrationNumberQueryCommand();
        String args[] = {};
        StringWriter writer = new StringWriter();
        command.executeCLICommand(args, writer);
        Assert.assertEquals("Invalid use of command - registration_numbers_for_cars_with_colour command requires the color to be specified", writer.toString());
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException{
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}