package org.reddys.automatick.exception;

public class InvalidSlotNumberException extends Throwable {

    private String message;

    public InvalidSlotNumberException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
