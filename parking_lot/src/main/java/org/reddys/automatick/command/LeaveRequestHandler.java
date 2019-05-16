package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.exception.InvalidSlotNumberException;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

public class LeaveRequestHandler implements IRequestHandler {

    @Override
    public void executeRequest(ParkingLotRequest request, IParkingLotServer server) {
        if (request.getArguments().length < 1) {
            server.respond("Invalid use of command - leave command requires slot number to be specified");
            return;
        }

        int slotNumber = Integer.parseInt(request.getArguments()[0]);
        try {
            ParkingLot.getInstance().leave(slotNumber);
            server.respond("Slot number " + slotNumber + " is free");
        }catch (InvalidSlotNumberException ise) {
            server.respond(ise.getMessage());
        }
    }
}
