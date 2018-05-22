package com.nikolay.imbirev.connector.queryform;

@FunctionalInterface
public interface CommandParserInterface {

    String parseCommand(String string);

}