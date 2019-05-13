package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.ParkingSlot;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class ColorVsRegistrationNumberQueryCommand implements CLICommand {

    public void executeCLICommand(String[] args, Writer outputWriter) throws IOException {
        if (args.length < 1) {
            outputWriter.write("Invalid use of command - registration_numbers_for_cars_with_colour command requires the color to be specified");
            return;
        }

        String color = args[0];

        List<ParkingSlot> filteredList = ParkingLot.getInstance().colorQuery (color);
        Iterator<ParkingSlot> slotsIterator = filteredList.iterator();
        if (filteredList.size() <= 0) {
            outputWriter.write("No vehicles found");
        }
        while (slotsIterator.hasNext()) {
            outputWriter.write(slotsIterator.next().getParkedVehicle().getRegistrationNumber());
            if (slotsIterator.hasNext()) {
                outputWriter.write (",");
            }
        }
    }
}
