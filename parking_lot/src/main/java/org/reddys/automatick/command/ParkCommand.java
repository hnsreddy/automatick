package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.ParkingFullException;

import java.io.IOException;
import java.io.Writer;

public class ParkCommand implements CLICommand {

    @Override
    public void executeCLICommand(String[] args, Writer outputWriter) throws IOException {
        if (args.length < 2) {
            outputWriter.write("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order\n");
            return;
        }
        String vehicleRegistrationNumber = args[0];
        String vehicleColor = args[1];

        try {
            int slotNumber = ParkingLot.getInstance().park (new Vehicle(vehicleRegistrationNumber, vehicleColor));
            outputWriter.write("Allocated slot number:" + slotNumber + "\n");
        } catch (ParkingFullException pfx) {
            outputWriter.write(pfx.getMessage() + "\n");
        }
    }
}
