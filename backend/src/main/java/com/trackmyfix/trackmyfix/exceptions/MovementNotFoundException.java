package com.trackmyfix.trackmyfix.exceptions;

public class MovementNotFoundException extends RuntimeException{
    public MovementNotFoundException(String message) {
        super(message);
    }
}
