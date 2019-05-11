package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;

import java.io.IOException;
import java.io.Writer;

public class RegistrationNumberVsSlotNumberQueryCommand implements CLICommand {

    public void executeCLICommand(String[] args, Writer outputWriter) throws IOException {
        if (args.length < 1) {
            outputWriter.write ("Invalid use of command - slot_number_for_registration_number command requires the registration number to be specified");
            return;
        }

        String registrationNumber = args[0];
        int slotNumber = -1;
        slotNumber = ParkingLot.getInstance().registrationNumberVsSlotNumberQuery (registrationNumber);

        if (slotNumber >= 0) {
            outputWriter.write(Integer.toString(slotNumber) + "\n");
        } else {
            outputWriter.write("Not found \n");
        }
    }
}
