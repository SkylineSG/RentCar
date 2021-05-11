package com.controller.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarConflictException extends Exception {
    private static final String MESSAGE = "Car already exist";

    public CarConflictException() {
        super(MESSAGE);
    }

}
