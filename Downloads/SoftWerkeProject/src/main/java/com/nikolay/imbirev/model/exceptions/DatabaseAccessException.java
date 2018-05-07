package com.nikolay.imbirev.model.exceptions;

/**
 * this exception throws when application cannot connect to the database
 */
public class DatabaseAccessException  extends Exception {

    private String message;

    public DatabaseAccessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}