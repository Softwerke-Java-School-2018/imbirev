package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.RequestCode;
import lombok.extern.log4j.Log4j;
import java.util.Arrays;

@Log4j
public class CommandParser {

    private static final char START_OF_SEARCH_CONDITIONS = '[';
    private static final char END_OF_SEARCH_CONDITIONS = ']';

    private static final char START_OF_SORT_CONDITIONS = '{';
    private static final char END_OF_SORT_CONDITIONS = '}';

    private static final char START_OF_INSERT_OR_UPDATE = '(';
    private static final char END_OF_INSERT_OR_UPDATE = ')';

    private static final String DELIMITER = ",";

    private static final String[] arrayOfKeyWords = {
         "create", "update", "get", "delete"
    };
    private static final String[] arrayOfEntities = {
            "sale", "client", "device"
    };

    private String operation;
    private String entity;

    private String searchConditions;
    private String sortColumns;
    private String insertOrUpdateString;

    private int delimiterCounter = 0;

    private QueryForm queryForm;

    public static CommandParser getCommandParser() {
        return new CommandParser();
    }

    /*
    sample of the requests
    create client (first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)
    delete client (first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997)
    update client [first_name = nikolai, second_name = imbirev] (first_name = new_name, date_of_birth = new_date)
    */

    private CommandParser() {
        queryForm = new QueryForm();
    }

    /**
     * in this method we divide string to some parts and make initial checks for errors
     * @param input is a initial string of the request
     * @return result code of query from QueryForm or enter error
     */
    public String parseCommand(String input) {

        CommandParserInterface commandParserInterface = (string -> {
            if (input == null) return RequestCode.ENTER_ERROR.toString();
            try {
                if (!initialCheck(input.trim())) {
                    log.error("initial checked failed");
                    return RequestCode.ENTER_ERROR.toString();
                }
            } catch (IllegalArgumentException e) {
                return RequestCode.ENTER_ERROR.toString();
            }

            String[] searchArray = getArray(searchConditions);
            log.info(Arrays.toString(searchArray) + "  ok");

            String[] sortArray = getArray(sortColumns);
            log.info(Arrays.toString(sortArray) + " ok");

            String[] insertOrUpdateArray = getArray(insertOrUpdateString);
            log.info(Arrays.toString(insertOrUpdateArray) + "  ok");
            return queryForm.createQuery(operation, entity, sortArray, searchArray, insertOrUpdateArray);
        });
        return commandParserInterface.parseCommand(input);
    }


    private boolean initialCheck(String string) {
        if (!getFirstCheck(string)) {
            log.error("first checker failed");
            return false;
        }
        return getBracketCheck(string);
    }


    /**
     * here we get initial checks for empty string or irrelevant first two key words
     * @param string is an initial string
     * @return true if everything is okey or false if first two words from the request are irrelevant
     */
    private boolean getFirstCheck(String string) {
        boolean flag;
        String[] inputString = string.split(" +");
        flag = Arrays.stream(arrayOfKeyWords).anyMatch(s -> s.equals(inputString[0].trim()))
                && Arrays.stream(arrayOfEntities).anyMatch(s -> s.equals(inputString[1].trim()));
        if (flag) {
            operation = inputString[0].trim();
            entity = inputString[1].trim();
            return true;
        }
        return false;
    }

    /**
     * here we check amount of brackets
     * @param string is an initial string
     * @return true if amount of brackets is good or false
     */
    private boolean getBracketCheck(String string) {
        String searchPart = getPart(string, START_OF_SEARCH_CONDITIONS, END_OF_SEARCH_CONDITIONS);
        log.info(searchPart + "  search part");
        String sortPart = getPart(string, START_OF_SORT_CONDITIONS, END_OF_SORT_CONDITIONS);
        log.info(sortPart + "  sort part");
        String insertOrUpdatePart = getPart(string, START_OF_INSERT_OR_UPDATE, END_OF_INSERT_OR_UPDATE);
        log.info(insertOrUpdatePart + "   insert part");
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
        log.info(delimiterCounter + " del counter");
        return delimiterCounter % 2 == 0;
    }

    /**
     * here we get string array from the string with ',' delimiter
     * @param input is initial string
     * @return new string array or null, if length of the string is null
     */
    private String[] getArray(String input) {
        if (input == null || input.trim().length() == 0) {
            log.warn("empty string[]");
            return new String[0];
        }
        return input.split(DELIMITER);
    }
    /**
     * here we get some part between brackets from the string
     * @param string is initial string
     * @param startBracket is a start position
     * @param endBracket is an end position
     * @return new part of string or null, if we have irrelevant brackets location
     * @throws IllegalArgumentException when we have some illegal arguments
     */
    private String getPart(String string, char startBracket, char endBracket) {
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
            if (localCounter > 2) {
                log.error(localCounter + " local counter error");
                throw new IllegalArgumentException();
            }
            delimiterCounter += localCounter;
            return string.substring(string.indexOf(startBracket) + 1,
                    string.indexOf(endBracket));
        } else throw new IllegalArgumentException();
    }
}