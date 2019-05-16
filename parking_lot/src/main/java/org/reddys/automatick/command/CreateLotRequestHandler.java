package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

public class CreateLotRequestHandler implements IRequestHandler {

    @Override
    public void executeRequest(ParkingLotRequest request, IParkingLotServer server) {
        if (request.getArguments().length < 1) {
            server.respond("Invalid use of command - create_parking_lot command requires number of slots to be specified");
            return;
        }

        int numberOfSlots;
        try {
            numberOfSlots = Integer.parseInt(request.getArguments()[0]);
        } catch (NumberFormatException nfe) {
            server.respond ("Invalid argument passed, cannot create a parking lot with specified arguments. Try and be a little more sensible");
            return;
        }

        if (ParkingLot.getInstance().getLimit() > 0) {
            server.respond("Parking lot already created");
            return;
        }
        ParkingLot.getInstance().setLimit (numberOfSlots);
        server.respond ("Created a parking lot with " + numberOfSlots + " slots");
    }

}
