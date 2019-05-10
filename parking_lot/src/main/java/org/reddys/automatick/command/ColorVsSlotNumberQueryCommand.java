package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.ParkingSlot;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class ColorVsSlotNumberQueryCommand implements CLICommand {

    public void executeCLICommand(String[] args, Writer outputWriter) throws IOException {
        if (args.length < 1) {
            outputWriter.write("Invalid use of command - slot_numbers_for_cars_with_colour command requires the color to be specified");
            return;
        }

        String color = args[0];

        List<ParkingSlot> filteredList = ParkingLot.getInstance().colorQuery (color);
        if (filteredList.isEmpty()) {
            outputWriter.write("No vehicles found");
        }
        Iterator <ParkingSlot> slotIterator = filteredList.iterator();
        while (slotIterator.hasNext()) {
            ParkingSlot slot = slotIterator.next();
            outputWriter.write(Integer.toString(slot.getSlotNumber()));

            if (slotIterator.hasNext()) {
                outputWriter.write(",");
            }
        }
    }
}
