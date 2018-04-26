package com.nikolay.imbirev.model.exceptions;

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
