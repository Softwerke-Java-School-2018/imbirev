package com.nikolay.imbirev.model.exceptions;

public class LocalDateParseException extends Exception {

    private String message;

    public LocalDateParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
