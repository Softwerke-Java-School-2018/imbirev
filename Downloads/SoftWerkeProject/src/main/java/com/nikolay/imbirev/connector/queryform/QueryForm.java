package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.*;
import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Arrays;
import java.util.logging.Logger;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class QueryForm {

    private final static Logger log = Logger.getAnonymousLogger();

    private DateParserInterface dateParser;

    private String operation;
    private String entity;
    private String[] sortArray;
    private String[] searchArray;
    private String[] insertOrUpdateArray;

    private Column[] sortColumns;
    private Query[] searchQueries;
    private Query[] insertOrUpdateQueries;

    public String createQuery() {
        log.info("operation " + operation);
        log.info("entity " + entity);
        log.info("sortArray " + Arrays.toString(sortArray));
        log.info("searchArray " + Arrays.toString(searchArray));
        log.info("insertOrUpdateArray " + Arrays.toString(insertOrUpdateArray));
        if (!checkForAvailableOperation(operation)) return RequestCode.DATA_ERROR.toString();
        try {
            sortColumns = getSortColumns();
            log.info("sort columns " + (sortColumns != null ? Arrays.toString(sortColumns) : null));
            searchQueries = getQuery(searchArray);
            log.info("search columns " + (searchQueries != null ? Arrays.toString(searchQueries) : null));
            insertOrUpdateQueries = getQuery(insertOrUpdateArray);
            log.info("insertOrUpdate columns " + (insertOrUpdateQueries != null ? Arrays.toString(insertOrUpdateQueries) : null));
        } catch (IllegalArgumentException e) {
            return RequestCode.DATA_ERROR.toString();
        }
        return performOperation().toString();
    }

    private RequestCode performOperation() {
        switch (entity) {
            case "client":
                return new ClientQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "device":
                return new DeviceQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "sale":
                return new SaleQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
                default: return RequestCode.DATA_ERROR;
        }
    }

    private boolean checkForAvailableOperation(@NotNull String operation) {
        switch (operation) {
            case "create":
            case "delete":
                if (sortArray != null) {
                    return false;
                }
                if (searchArray != null) {
                    return false;
                }
                return insertOrUpdateArray != null;
            case "update":
                if (sortArray != null) {
                    return false;
                }
                return searchArray != null && insertOrUpdateArray != null;
            case "get":
                return insertOrUpdateArray == null;
            default:
                return false;
        }
    }

    private Column[] getSortColumns() {
        if (sortArray == null) return null;
        Column[] resultArray = new Column[sortArray.length];
        for (int i = 0; i < sortArray.length; i++) {
            String queryName = sortArray[i].trim();
            for (String tableName : getAllTablesColumns()) {
                if (queryName.equals(tableName))  resultArray[i] = Column.builder().columnName(queryName).build();
            }
        }
        return resultArray;
    }

    private String[] getAllTablesColumns() {
        String[] resultArray = new String[SaleTable.Cols.columns.length + ClientTable.Cols.columns.length + DeviceTable.Cols.columns.length];
        int counter = 0;
        while (counter < resultArray.length){
            for (Column column : SaleTable.Cols.columns) {
                resultArray[counter] = column.getColumnName();
                counter++;
            }
            for (Column column : ClientTable.Cols.columns) {
                resultArray[counter] = column.getColumnName();
                counter++;
            }
            for (Column column : DeviceTable.Cols.columns) {
                resultArray[counter] = column.getColumnName();
                counter++;
            }
        }
        return resultArray;
    }

    private Query[] getQuery(String[] initialArray) {
        if (initialArray == null) return null;
        int num = 0;
        Query[] resultArray = new Query[initialArray.length];
        for (String item : initialArray) {
            String[] itemParts = item.split("=");
            if (num >= resultArray.length) throw new IllegalArgumentException();
            resultArray[num++] = new Query(itemParts[0].trim(), itemParts[1].trim());
        }
        return resultArray;
    }
}