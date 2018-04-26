package com.nikolay.imbirev.model.exceptions;

public class SQLWarningException extends Exception {
    private String message;

    public SQLWarningException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
