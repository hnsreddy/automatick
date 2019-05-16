package org.reddys.automatick.command;


import org.reddys.automatick.factory.RequestHandlerFactory;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;
import org.reddys.automatick.network.ParkingLotServerImpl;

public class ParkingLotApplication {

    public static void main(String[] args) {
        IParkingLotServer server = new ParkingLotServerImpl();
            while (true) {
                server.createAndListen(56060);
                ParkingLotRequest request = server.buildRequest();
                if (request.getCommand().toLowerCase().trim().equals("exit")) {
                    server.close();
                    System.exit(0);
                }

                RequestHandlerFactory.getRequestHandler(request).executeRequest(request, server);
                server.close();

            }
    }
}
