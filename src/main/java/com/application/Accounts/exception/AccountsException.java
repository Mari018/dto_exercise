package com.application.Accounts.exception;

import com.application.Accounts.error.Error;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountsException {

    @ExceptionHandler(value = {
            PlateNotFoudException.class,
            UserNotFoundException.class,
            VehicleNotfoundException.class })
    public ResponseEntity<Error> handleNotFound(Exception e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.builder()
                .message(e.getMessage())
                .method(request.getMethod())
                .requestURI(request.getRequestURI())
                .build());

    }
}
