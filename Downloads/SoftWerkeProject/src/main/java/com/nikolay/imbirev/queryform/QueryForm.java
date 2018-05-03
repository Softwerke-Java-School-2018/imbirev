package com.nikolay.imbirev.queryform;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Arrays;
import java.util.logging.Logger;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class QueryForm {

    private final static Logger log = Logger.getAnonymousLogger();

    private String operation;
    private String entity;
    private String[] sortArray;
    private String[] searchArray;
    private String[] insertOrUpdateArray;

    public String createQuery() {
        log.info("operation " + operation);
        log.info("entity " + entity);
        log.info("sortArray " + Arrays.toString(sortArray));
        log.info("searchArray " + Arrays.toString(searchArray));
        log.info("insertOrUpdateArray " + Arrays.toString(insertOrUpdateArray));
        return null;
    }


}
