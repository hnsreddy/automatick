package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.exception.InvalidSlotNumberException;

import java.io.IOException;
import java.io.Writer;

public class LeaveCommand implements CLICommand {

    public void executeCLICommand(String[] args,  Writer outputWriter) throws IOException {
        if (args.length < 1) {
            outputWriter.write("Invalid use of command - leave command requires slot number to be specified");
            return;
        }

        int slotNumber = Integer.parseInt(args[0]);
        try {
            ParkingLot.getInstance().leave(slotNumber);
            outputWriter.write("Slot number " + slotNumber + " is free");
        }catch (InvalidSlotNumberException ise) {
            outputWriter.write(ise.getMessage());
        }
    }
}
