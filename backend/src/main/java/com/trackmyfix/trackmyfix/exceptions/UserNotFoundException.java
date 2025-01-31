package com.trackmyfix.trackmyfix.exceptions;

import javax.management.relation.RelationNotFoundException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
