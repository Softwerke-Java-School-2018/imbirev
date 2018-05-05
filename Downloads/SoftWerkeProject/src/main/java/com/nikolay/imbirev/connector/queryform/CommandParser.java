package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.RequestCode;
import com.sun.istack.internal.NotNull;
import java.util.logging.Logger;

public class CommandParser implements CommandParserInterface {

    private final static char startOfSearchConditions = '[';
    private final static char endOfSearchConditions = ']';

    private final static char startOfSortConditions = '{';
    private final static char endOfSortConditions = '}';

    private final static char startOfInsertOrUpdate = '(';
    private final static char endOfInsertOrUpdate = ')';

    private final static String delimiter = ",";

    private final static String[] arrayOfKeyWords = {
         "create", "update", "get", "delete"
    };
    private final static String[] arrayOfEntities = {
            "sale", "client", "device"
    };

    private String operation;
    private String entity;

    private String searchConditions;
    private String sortColumns;
    private String insertOrUpdateString;

    private int delimiterCounter = 0;

    private final static Logger log = Logger.getAnonymousLogger();

    /*
    sample of the requests
    create client (first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)
    delete client (first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997)
    get client [first_name = nikolai, second_name = imbirev] {date_of_birth}
    update client [first_name = nikolai, second_name = imbirev] (first_name = new_name, date_of_birth = new_date)
    */

    /**
     * in this method we divide string to some parts and make initial checks for errors
     * @param string is a initial string of the request
     * @return result code of query from QueryForm or enter error
     */
    @Override
    public String parseCommand(@NotNull String string) {
        if (string == null || string.equals("")) return RequestCode.ENTER_ERROR.toString();
        if (!getFirstCheck(string)) return RequestCode.ENTER_ERROR.toString();
        if (!getBracketCheck(string)) return RequestCode.ENTER_ERROR.toString();
        QueryForm.QueryFormBuilder builder = QueryForm.builder();
        builder.entity(entity).operation(operation);
        if (searchConditions != null) {
            if (!searchConditions.equals(RequestCode.ENTER_ERROR.toString())) {
                String[] searchArray = getArray(searchConditions);
                builder.searchArray(searchArray);
            } else return RequestCode.ENTER_ERROR.toString();
        }
        if (sortColumns != null) {
        if (!sortColumns.equals(RequestCode.ENTER_ERROR.toString())) {
            String[] sortArray = getArray(sortColumns);
            builder.sortArray(sortArray);
        } else return RequestCode.ENTER_ERROR.toString();
        }
        if (insertOrUpdateString != null) {
        if (!insertOrUpdateString.equals(RequestCode.ENTER_ERROR.toString())) {
            String[] insertOrUpdateArray = getArray(insertOrUpdateString);
            builder.insertOrUpdateArray(insertOrUpdateArray);
        } else return RequestCode.ENTER_ERROR.toString();
        }
        QueryForm queryForm = builder.build();
        return queryForm.createQuery();
    }

    /**
     * here we get initial checks for empty string or irrelevant first two key words
     * @param string is an initial string
     * @return true if everything is okey or false if first two words from the request are irrelevant
     */
    private boolean getFirstCheck(@NotNull String string) {
        String[] input = string.split(" +");
        for (String word : arrayOfKeyWords) {
            if (input[0].trim().equals(word)) {
                for (String entityName : arrayOfEntities) {
                    if (input[1].trim().equals(entityName)) {
                        operation = input[0].trim();
                        entity = input[1].trim();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * here we check amount of brackets
     * @param string is an initial string
     * @return true if amount of brackets is good or false
     */
    private boolean getBracketCheck(@NotNull String string) {
        String searchPart = getPart(string, startOfSearchConditions, endOfSearchConditions);
        log.info(searchPart + " sp");
        String sortPart = getPart(string, startOfSortConditions, endOfSortConditions);
        log.info(sortPart + " sop");
        String insertOrUpdatePart = getPart(string, startOfInsertOrUpdate, endOfInsertOrUpdate);
        log.info(insertOrUpdatePart + " ip");
        if (searchPart != null) {
            searchConditions = searchPart;
        }
        if (sortPart != null) {
            sortColumns = sortPart;
        }
        if (insertOrUpdatePart != null) {
            insertOrUpdateString = insertOrUpdatePart;
        }
        if (delimiterCounter == 0) return false;
        return delimiterCounter % 2 == 0;
    }

    /**
     * here we get string array from the string with ',' delimiter
     * @param input is initial string
     * @return new string array or null, if length of the string is null
     */
    private String[] getArray(@NotNull String input) {
        if (input.trim().length() == 0) return null;
        return input.split(delimiter);
    }

    /**
     * here we get some part between brackets from the string
     * @param string is initial string
     * @param startBracket is a start position
     * @param endBracket is an end position
     * @return new part of string or null, if we have irrelevant brackets location
     */
    private String getPart(@NotNull String string, @NotNull char startBracket, @NotNull char endBracket) {
        int localCounter = 0;
        for (char letter : string.toCharArray()) {
            if (letter == startBracket) {
                localCounter++;
                for (char let : string.substring(string.indexOf(startBracket) + 1).toCharArray()) {
                    if (let == endBracket) {
                        localCounter++;
                    }
                }
            }
        }
        if (localCounter == 0) {
            return null;
        }
        if (localCounter > 1) {
            log.info(localCounter + " localCounter");
            if (localCounter > 2) return RequestCode.ENTER_ERROR.toString();
            delimiterCounter += localCounter;
            return string.substring(string.indexOf(startBracket) + 1,
                    string.indexOf(endBracket));
        } else return RequestCode.ENTER_ERROR.toString();
    }
}