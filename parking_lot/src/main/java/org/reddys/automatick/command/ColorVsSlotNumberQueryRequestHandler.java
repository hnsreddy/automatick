package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.ParkingSlot;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

import java.util.Iterator;
import java.util.List;

public class ColorVsSlotNumberQueryRequestHandler implements IRequestHandler {

    @Override
    public void executeRequest(ParkingLotRequest request, IParkingLotServer server) {
        if (request.getArguments().length < 1) {
            server.respond("Invalid use of command - slot_numbers_for_cars_with_colour command requires the color to be specified");
            return;
        }

        String color = request.getArguments()[0];

        List<ParkingSlot> filteredList = ParkingLot.getInstance().colorQuery (color);
        if (filteredList.isEmpty()) {
            server.respond("No vehicles found");
        }
        Iterator <ParkingSlot> slotIterator = filteredList.iterator();
        while (slotIterator.hasNext()) {
            ParkingSlot slot = slotIterator.next();
            server.respond(Integer.toString(slot.getSlotNumber()));
        }
    }
}
