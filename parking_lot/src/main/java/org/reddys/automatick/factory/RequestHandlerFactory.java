package org.reddys.automatick.factory;

import org.reddys.automatick.command.*;
import org.reddys.automatick.network.ParkingLotRequest;

public class RequestHandlerFactory {

    public static IRequestHandler getRequestHandler(ParkingLotRequest request) {
        switch (request.getCommand()) {
            case "park" :
                return new ParkRequestHandler();
            case "leave" :
                return new LeaveRequestHandler();
            case "create_lot":
                return new CreateLotRequestHandler();
            case "registration_numbers_for_cars_with_colour":
                return new ColorVsRegistrationNumberQueryRequestHandler();
            case "slot_numbers_for_cars_with_colour":
                return new ColorVsSlotNumberQueryRequestHandler();
            case "slot_number_for_registration_number":
                return new RegistrationNumberVsSlotNumberQueryRequestHandler();
            default:
                return null;
        }
    }
}
