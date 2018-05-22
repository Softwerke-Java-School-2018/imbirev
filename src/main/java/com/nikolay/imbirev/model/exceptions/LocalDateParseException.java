package com.nikolay.imbirev.model.exceptions;

public class LocalDateParseException extends Exception {

    private final String message;

    public LocalDateParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}