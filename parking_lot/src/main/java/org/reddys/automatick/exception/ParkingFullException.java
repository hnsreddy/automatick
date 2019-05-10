package org.reddys.automatick.exception;

public class ParkingFullException extends Exception {

    @Override
    public String getMessage() {
        return "Sorry, parking lot is full";
    }
}
