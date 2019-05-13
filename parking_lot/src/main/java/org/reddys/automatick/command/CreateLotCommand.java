package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.exception.InvalidLimitException;

import java.io.IOException;
import java.io.Writer;

public class CreateLotCommand implements CLICommand {

    public void executeCLICommand(String[] args, Writer outputWriter) throws IOException {
        if (args.length < 1) {
            outputWriter.write("Invalid use of command - create_parking_lot command requires number of slots to be specified");
            return;
        }

        int numberOfSlots;
        try {
            numberOfSlots = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            outputWriter.write ("Invalid argument passed, cannot create a parking lot with specified arguments. Try and be a little more sensible");
            return;
        }

        if (ParkingLot.getInstance().getLimit() > 0) {
            outputWriter.write("Parking lot already created");
            return;
        }
        try {
            ParkingLot.getInstance().setLimit (numberOfSlots);
            outputWriter.write ("Created a parking lot with " + numberOfSlots + " slots");
        } catch (InvalidLimitException e) {
            outputWriter.write(e.getMessage());
        }
    }
}
