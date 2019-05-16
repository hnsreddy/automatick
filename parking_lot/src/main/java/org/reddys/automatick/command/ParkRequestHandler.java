package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.ParkingFullException;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

public class ParkRequestHandler implements IRequestHandler {

    @Override
    public void executeRequest(ParkingLotRequest request, IParkingLotServer server) {
        if (request.getArguments().length < 2) {
            server.respond("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order");
            return;
        }
        String vehicleRegistrationNumber = request.getArguments()[0];
        String vehicleColor = request.getArguments()[1];

        try {
            int slotNumber = ParkingLot.getInstance().park (new Vehicle(vehicleRegistrationNumber, vehicleColor));
            server.respond("Allocated slot number:" + slotNumber);
        } catch (ParkingFullException pfx) {
            server.respond(pfx.getMessage());
        }
    }
}
