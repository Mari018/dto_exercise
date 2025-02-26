package com.application.Accounts.exception;

public class VehicleNotfoundException extends RuntimeException {
    public VehicleNotfoundException(String message) {
        super(message);
    }

  public VehicleNotfoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
