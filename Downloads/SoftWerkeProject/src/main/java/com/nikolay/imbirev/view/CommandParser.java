package com.nikolay.imbirev.view;

import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.queryform.QueryForm;

import java.util.logging.Logger;

public class CommandParser implements CommandParserInterface {

    private final static Logger log = Logger.getAnonymousLogger();

    private final static char startOfSearchConditions = '[';
    private final static char endOfSearchConditions = ']';

    private final static char startOfSortConditions = '{';
    private final static char endOfSortConditions = '}';

    private final static char startOfInsertOrUpdate = '(';
    private final static char endOfInsertOrUpdate = ')';

    private final static String delimiter = ",";

    private final static String[] arrayOfKeyWords = {
         "create", "update", "get", "delete", "set"
    };
    private final static String[] arrayOfEntities = {
            "sale", "client", "device"
    };

    private String operation;
    private String entity;

    private String searchConditions;
    private String sortColumns;
    private String insertOrUpdateString;

    // create client (first_name = nikolay, second_name = imbirev, date_of_birth = 30/06/1997)
    // delete client (first_name = nikolay, second_name = imbirev, date_of_birth = 30/06/1997)
    // get client [first_name = nikolay, second_name = imbirev] {date_of_birth}
    // update client [first_name = nikolay, second_name = imbirev] set (first_name = niko, date_of_birth = 30/07/1997)

    /**
     * in this method we divide string to some parts
     * @param string is a initial string
     * @return result code of query
     */
    @Override
    public String parseCommand(String string) {
        if (string.equals("")) return RequestCode.ENTER_ERROR.toString();
        if (!getFirstCheck(string)) return RequestCode.ENTER_ERROR.toString();
        if (!getBracketCheck(string)) return RequestCode.ENTER_ERROR.toString();
        QueryForm.QueryFormBuilder builder = QueryForm.builder();
        builder.entity(entity).operation(operation);
        if (searchConditions != null) {
            String[] searchArray = getArray(searchConditions);
            builder.searchArray(searchArray);
        }
        if (sortColumns != null) {
            String[] sortArray = getArray(sortColumns);
            builder.sortArray(sortArray);
        }
        if (insertOrUpdateString != null) {
            String[] insertOrUpdateArray = getArray(insertOrUpdateString);
            builder.insertOrUpdateArray(insertOrUpdateArray);
        }
        QueryForm queryForm = builder.build();
        return queryForm.createQuery();
    }

    private boolean getFirstCheck(String string) {
        String[] input = string.split(" +");
        for (String word : arrayOfKeyWords) {
            if (input[0].trim().equals(word)) {
                for (String entityName : arrayOfEntities) {
                    if (input[1].trim().equals(entityName)) {
                        log.info("operation  " + input[0]);
                        log.info("entity  " + input[1]);
                        operation = input[0].trim();
                        entity = input[1].trim();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean getBracketCheck(String string) {
        int delimiterCounter = 0;
        String searchPart = null;
        String sortPart = null;
        String insertOrUpdatePart = null;
        for (char letter : string.toCharArray()) {
            if (letter == startOfSearchConditions || letter == endOfSearchConditions) {
                searchPart = string.substring(string.indexOf(startOfSearchConditions)+1,
                        string.indexOf(endOfSearchConditions));
                log.info("search part " + searchPart);
                delimiterCounter++;
            }
            if(letter == startOfSortConditions || letter == endOfSortConditions) {
                sortPart = string.substring(string.indexOf(startOfSortConditions)+1,
                        string.indexOf(endOfSortConditions));
                log.info("sort part " + sortPart);
                delimiterCounter++;
            }
            if (letter == startOfInsertOrUpdate || letter == endOfInsertOrUpdate) {
                insertOrUpdatePart = string.substring(string.indexOf(startOfInsertOrUpdate)+1,
                        string.indexOf(endOfInsertOrUpdate));
                log.info("insert or update part " + insertOrUpdatePart);
                delimiterCounter++;
            }
        }
        if (searchPart != null) {
            searchConditions = searchPart;
        }
        if (sortPart != null) {
            sortColumns = sortPart;
        }
        if (insertOrUpdatePart != null) {
            insertOrUpdateString = insertOrUpdatePart;
        }
        log.info(String.valueOf(delimiterCounter));
        return delimiterCounter % 2 == 0;
    }

    private String[] getArray(String input) {
        return input.split(delimiter);
    }
}