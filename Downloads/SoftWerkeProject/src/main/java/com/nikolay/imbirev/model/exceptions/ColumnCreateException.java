package com.nikolay.imbirev.model.exceptions;

public class ColumnCreateException extends Exception {

    private String message;

    public ColumnCreateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
