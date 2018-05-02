package com.nikolay.imbirev.view;

import java.util.logging.Logger;

public class CommandParser implements CommandParserInterface {

    private final static Logger log = Logger.getAnonymousLogger();

    private final static char startOfSearchConditions = '[';
    private final static char endOfSearchConditions = ']';

    private final static char startOfSortConditions = '{';
    private final static char endOfSortConditions = '}';

    private final static char delimiter = ',';

    private final static char queryDelimiter = '=';

    private final static char startOfInsertOrUpdate = '(';
    private final static char endOfInsertOrUpdate = ')';
    

    @Override
    public String parseCommand(String string) {
        for (char letter : string.toCharArray()) {

        }
        return "good";
    }
}