package org.reddys.automatick.exception;

public class InvalidLimitException extends Exception {

    private String message;

    public InvalidLimitException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
