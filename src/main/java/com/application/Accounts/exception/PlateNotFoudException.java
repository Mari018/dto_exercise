package com.application.Accounts.exception;

public class PlateNotFoudException extends RuntimeException {
    public PlateNotFoudException(String message) {
        super(message);
    }

    public PlateNotFoudException(String message, Throwable cause) {
        super(message, cause);
    }
}
