package org.reddys.automatick.factory;

import org.junit.Assert;
import org.junit.Test;
import org.reddys.automatick.command.CLICommand;

import static org.junit.Assert.*;

public class CLICommandFactoryTest {

    @Test
    public void getParkCommand() {
        CLICommand command = CLICommandFactory.getCommand("park");
        Assert.assertNotNull(command);
    }

    @Test
    public void getLeaveCommand() {
        CLICommand command = CLICommandFactory.getCommand("leave");
        Assert.assertNotNull(command);
    }

    @Test
    public void getCreateLotCommand() {
        CLICommand command = CLICommandFactory.getCommand("create_lot");
        Assert.assertNotNull(command);
    }

    @Test
    public void getColorVsRegsitrationNumberCommand() {
        CLICommand command = CLICommandFactory.getCommand("registration_numbers_for_cars_with_colour");
        Assert.assertNotNull(command);
    }

    @Test
    public void getColorVsSlotNumberCommand() {
        CLICommand command = CLICommandFactory.getCommand("slot_numbers_for_cars_with_colour");
        Assert.assertNotNull(command);
    }

    @Test
    public void getRegistrationNumberVsSlotNumberCommand() {
        CLICommand command = CLICommandFactory.getCommand("slot_number_for_registration_number");
        Assert.assertNotNull(command);
    }

    @Test
    public void getCommandInvalid() {
        CLICommand command = CLICommandFactory.getCommand("Park");
        Assert.assertNull(command);
    }

}