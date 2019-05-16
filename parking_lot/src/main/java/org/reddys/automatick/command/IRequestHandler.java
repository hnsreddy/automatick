package org.reddys.automatick.command;

import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

public interface IRequestHandler {

    void executeRequest (ParkingLotRequest request, IParkingLotServer server);
}
