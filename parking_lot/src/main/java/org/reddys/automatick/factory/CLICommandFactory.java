package org.reddys.automatick.factory;

import org.reddys.automatick.command.*;

public class CLICommandFactory {

    public static CLICommand getCommand(String commandType) {
        switch (commandType) {
            case "park" :
                return new ParkCommand();
            case "leave" :
                return new LeaveCommand();
            case "create_lot":
                return new CreateLotCommand();
            case "registration_numbers_for_cars_with_colour":
                return new ColorVsRegistrationNumberQueryCommand();
            case "slot_numbers_for_cars_with_colour":
                return new ColorVsSlotNumberQueryCommand();
            case "slot_number_for_registration_number":
                return new RegistrationNumberVsSlotNumberQueryCommand();
            default:
                return null;
        }
    }
}
