package com.nikolay.imbirev.view;

import java.util.logging.Logger;

public class CommandParser implements CommandParserInterface {

    private final static Logger log = Logger.getAnonymousLogger();

    @Override
    public String parseCommand(String string) {
        log.info("starting parse " + string);
        String[] parts = string.split(" +");
        for (String a : parts) {
            log.info(a);
        }
        return "good";
    }
}
