package com.nikolay.imbirev.model.exceptions;

/**
 * this exception throws when result set or statement to the database contains warnings
 */
public class SQLWarningException extends Exception {

    private final String message;

    public SQLWarningException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}