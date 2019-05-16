package org.reddys.automatick.network;

public interface IParkingLotServer {
    void createAndListen(int port);
    ParkingLotRequest buildRequest();
    void respond(String str);
    void close();
}
