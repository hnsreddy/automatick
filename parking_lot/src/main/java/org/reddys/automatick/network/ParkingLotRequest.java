package org.reddys.automatick.network;

import java.util.Arrays;

public class ParkingLotRequest {

    private String command;

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    private String arguments[];

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public static ParkingLotRequest parseRequest(String fullCommandString) {
        ParkingLotRequest request = new ParkingLotRequest();

        String[] fullCommandArray = fullCommandString.split(" ");
        request.setCommand(fullCommandArray[0]);

        String[] arguments = Arrays.copyOfRange(fullCommandArray, 1, 3);
        request.setArguments(arguments);
        return request;
    }
}
