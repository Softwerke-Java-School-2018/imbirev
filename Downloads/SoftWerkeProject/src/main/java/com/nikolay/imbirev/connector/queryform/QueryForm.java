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

    /**
     * here we delegate continuous operation to each entity performer
     * @return request of them operation
     */
    private RequestCode performOperation() {
        switch (entity) {
            case "client":
                return RequestCode.SUCCESS;
                //return new ClientQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "device":
                return RequestCode.SUCCESS;
               // return new DeviceQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "sale":
                return RequestCode.SUCCESS;
               // return new SaleQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
                default: return RequestCode.DATA_ERROR;
        }
    }

    /**
     * here we check for avaliable conditions for each operation
     * @param operation is a name of operation
     * @return true if all conditions are good or false
     */
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

    /**
     * here we perform new array of columns if we want to sort query
     * @return new array of columns or null (if initial array is null) or throw IllegalArgumentException
     * if we have illegal columns here
     */
    private Column[] getSortColumns() {
        if (sortArray == null) return null;
        Column[] resultArray = new Column[sortArray.length];
        int counter = 0;
        for (int i = 0; i < sortArray.length; i++) {
            String queryName = sortArray[i].trim();
            for (String tableName : getAllTablesColumns()) {
                if (queryName.equals(tableName))  {
                    if (queryName.contains("=,.:;")) throw new IllegalArgumentException();
                    resultArray[i] = Column.builder().columnName(queryName).build();
                    counter++;
                }
            }
        }
        if (counter == 0) throw new IllegalArgumentException();
        return resultArray;
    }

    /**
     * in this method we get all tables columns to check names of the query or sortArray
     * @return string array of all columns
     */
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

    /**
     * here we perform query of arguments
     * @param initialArray from this string array
     * @return new query array or throw IllegalArgumentException (or null, if initialArray is empty)
     */
    private Query[] getQuery(String[] initialArray) {
        if (initialArray == null) return null;
        if (!checkForEquals(initialArray.length, initialArray)) throw new IllegalArgumentException();
        int num = 0;
        int counter = 0;
        Query[] resultArray = new Query[initialArray.length];
        for (String item : initialArray) {
            String[] itemParts = item.split("=");
            if (num >= resultArray.length) throw new IllegalArgumentException();
            String nameColumnItem = itemParts[0].trim();
            for (String colName : getAllTablesColumns()) {
                if (nameColumnItem.equals(colName)) counter++; // if we have this column in entities -> increment counter
            }
            if (counter == 0) throw new IllegalArgumentException(); // we have illegal column
            resultArray[num++] = new Query(nameColumnItem, itemParts[1].trim());
        }
        return resultArray;
    }

    /**
     * here we check for illegal amount of '=' in the scope
     * @param num is an illegal number
     * @param array is a string array is needed to check
     * @return true if okay or false if it is illegal
     */
    private boolean checkForEquals(int num, String[] array) {
        int counter = 0;
        for (String s : array) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '=') {
                    counter++;
                }
            }
        }
        return counter == num;
    }
}