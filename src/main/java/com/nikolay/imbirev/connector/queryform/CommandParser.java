package com.nikolay.imbirev.connector.queryform;

import com.mysql.cj.core.util.StringUtils;
import com.nikolay.imbirev.model.entities.RequestCode;
import lombok.extern.log4j.Log4j;
import java.util.*;
import java.util.regex.*;

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
     * parsing string is going in three stages:
     * 1 - check correctness
     * 2 - extract data from parts
     * 3 - send parts to the next class
     * @return request code of the operation or enter error if we have some troubles with input
     */
    public String parseCommand(String input) {
        CommandParserInterface commandParserInterface = (string -> {
            if (Objects.isNull(input)) return RequestCode.ENTER_ERROR.toString();
            try {
                if (!initialCheck(input.trim())) {
                    log.error("initial checked failed");
                    return RequestCode.ENTER_ERROR.toString();
                }
            } catch (IllegalArgumentException e) {
                return RequestCode.ENTER_ERROR.toString();
            }

            String[] searchArray = getArray(searchConditions);
            log.info(Arrays.toString(searchArray) + "  search Conditions");

            String[] sortArray = getArray(sortColumns);
            log.info(Arrays.toString(sortArray) + " sort Columns");

            String[] insertOrUpdateArray = getArray(insertOrUpdateString);
            log.info(Arrays.toString(insertOrUpdateArray) + "  insert or update part");
            return queryForm.createQuery(operation, entity, sortArray, searchArray, insertOrUpdateArray);
        });
        return commandParserInterface.parseCommand(input);
    }

    /**
     * checked method (only for check initial string)
     * @return true if all checks are correct or false
     */
    private boolean initialCheck(String string) {
        if (!getFirstCheck(string)) {
            log.error("first checker failed");
            return false;
        }
        return getBracketCheck(string);
    }

    /**
     * initial string is split by parts with some tabs or spaces by stream
     * extracting entity name and operation done by stream api (we also trim them to cut all spaces or tabs)
     * @param string is initial string
     * @return true if we have match of entity and operation name from arrays with key words (see arrays at the beginning at the class)
     * or false if we have no relevant matches
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
     * extracting parts from initial string is going here with getting startBracket and endBracket
     * search query - 0 code
     * sort query - 1 code
     * insert or update query - -1 code
     * these codes are using below
     * @param string is initial string
     * @return true or throw IllegalArgumentException if we have some illegal code
     */
    private boolean getBracketCheck(String string) {
        String searchPart = getPart(string, START_OF_SEARCH_CONDITIONS, END_OF_SEARCH_CONDITIONS, 0);
        String sortPart = getPart(string, START_OF_SORT_CONDITIONS, END_OF_SORT_CONDITIONS, 1);
        String insertOrUpdatePart = getPart(string, START_OF_INSERT_OR_UPDATE, END_OF_INSERT_OR_UPDATE, -1);
        searchConditions = searchPart;
        sortColumns = sortPart;
        insertOrUpdateString = insertOrUpdatePart;
        return true;
    }

    /**
     * this methods is necessary only for dividing some string into the array by divider
     * @param input string
     * @return array splitted by divider or empty string if input is null or empty
     */
    private String[] getArray(String input) {
        if (StringUtils.isNullOrEmpty(input)) {
            log.warn("empty string[]");
            return new String[0];
        }
        return input.trim().split(DELIMITER);
    }

    /**
     * getting part from the initial string is necessary for performing query to database
     * all types of query have their own code (see method getBracketsCheck)
     * then we call special method to extract part from the initial string
     * @return result of the operation or throws IllegalArgumentException if we have illegal code of the query
     */
    private String getPart(String string, char startBracket, char endBracket, int code) {
        Pattern searchPattern = Pattern.compile("(.*)(\\[[^]\\[{}()]*])(.*)");
        Pattern sortPattern = Pattern.compile("(.*)(\\{[^]\\[{}()]*})(.*)");
        Pattern insertOrUpdatePattern = Pattern.compile("(.*)(\\([^]\\[{}()]*\\))(.*)");
        switch (code) {
            case 1:
                return getString(string, startBracket, endBracket, sortPattern);
            case 0:
                return getString(string, startBracket, endBracket, searchPattern);
            case -1:
                return getString(string, startBracket, endBracket, insertOrUpdatePattern);
            default: throw new IllegalArgumentException();
        }
    }

    /**
     * get string from the part of input command is getting here:
     * firstly we get part from startBracket to endBracket using one of the patterns (see getPart method)
     * then we checked it for matcher and get 2 group from regex expression
     * example : update client [first_name = nikolai, second_name = imbirev] (first_name = new_name, date_of_birth = new_date)
     * update client - 1 group
     * [first_name = nikolai, second_name = imbirev] - 2 group
     * (first_name = new_name, date_of_birth = new_date) - 3 group
     * @return null if we have empty group (ex. get client [] {}) catching IndexOutOfBoundsException or we have no matches here or return part without brackets
     * ex. update client [first_name = nikolai, second_name = imbirev]
     * return - first_name = nikolai, second_name = imbirev
     */
    private String getString(String string, char startBracket, char endBracket, Pattern pattern) {
        try {
            String part = string.substring(string.indexOf(startBracket),
                    string.indexOf(endBracket) + 1);
            log.info(part + " part");
            Matcher matcher = pattern.matcher(part);
            if (matcher.matches()) {
                String group = matcher.group(2);
                log.info(group);
                return group.substring(group.indexOf(startBracket) + 1, group.indexOf(endBracket));
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}