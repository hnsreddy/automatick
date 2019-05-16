package org.reddys.automatick.command;

import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

public class RegistrationNumberVsSlotNumberQueryRequestHandler implements IRequestHandler {

    @Override
    public void executeRequest(ParkingLotRequest request, IParkingLotServer server) {
        if (request.getArguments().length < 1) {
            server.respond("Invalid use of command - slot_number_for_registration_number command requires the registration number to be specified");
            return;
        }

        String registrationNumber = request.getArguments()[0];
        int slotNumber = -1;
        slotNumber = ParkingLot.getInstance().registrationNumberVsSlotNumberQuery(registrationNumber);

        if (slotNumber >= 0) {
            server.respond(Integer.toString(slotNumber));
        } else {
            server.respond("Not found");
        }
    }
}