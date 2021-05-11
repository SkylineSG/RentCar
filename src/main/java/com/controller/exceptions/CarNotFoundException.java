package com.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends Exception{
    private static final String MESSAGE = "Car not found";

    public CarNotFoundException() {
        super(MESSAGE);
    }
}
