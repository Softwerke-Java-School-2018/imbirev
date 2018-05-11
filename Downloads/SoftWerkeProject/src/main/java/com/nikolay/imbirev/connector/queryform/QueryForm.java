package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.LocalDateParseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Log
class QueryForm {


    private DateParserInterface dateParser;

    private String operation;
    private String entity;
    private String[] sortArray;
    private String[] searchArray;
    private String[] insertOrUpdateArray;

    private Column[] sortColumns;
    private Query[] searchQueries;
    private Query[] insertOrUpdateQueries;

    String createQuery() {
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
        } catch (LocalDateParseException e) {
            return RequestCode.DATE_PARSING_ERROR.toString();
        }
        return performOperation().toString();
    }

    /**
     * here we delegate continuous operation to each entity performer
     * @return request of them operation
     */
    private RequestCode performOperation() {
        switch (entity.trim()) {
            case "client":
                return ClientQueryForm.getClientQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "device":
                return DeviceQueryForm.getClientQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
            case "sale":
               return SaleQueryForm.getClientQueryForm(operation, sortColumns, searchQueries, insertOrUpdateQueries).performOperation();
               default: return RequestCode.DATA_ERROR;
        }
    }

    /**
     * here we check for avaliable conditions for each operation
     * @param operation is a name of operation
     * @return true if all conditions are good or false
     */
    private boolean checkForAvailableOperation(@NonNull String operation) {
        switch (operation.trim()) {
            case "create":
            case "delete":
                return insertOrUpdateArray != null;
            case "update":
                return searchArray != null && insertOrUpdateArray != null;
            case "get":
                return true;
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
     * @return string array of all columns names
     */
    private String[] getAllTablesColumns() {
        List<String> resultArray = new ArrayList<>();
        for (Column column : SaleTable.Cols.COLUMNS) {
            resultArray.add(column.getColumnName());
        }
        for (Column column : ClientTable.Cols.COLUMNS) {
            resultArray.add(column.getColumnName());
        }
        for (Column column : DeviceTable.Cols.COLUMNS) {
            resultArray.add(column.getColumnName());
        }
        String[] strings = new String[resultArray.size()];
        strings = resultArray.toArray(strings);
        return strings;
    }

    /**
     * here we perform query of arguments (if we have column with data -> try to convert it to the LocalDate)
     * @param initialArray from this string array
     * @return new query array or throw IllegalArgumentException (or null, if initialArray is empty)
     * or throw LocalDateParseException if we cannot convert input to LocalDate
     */
    private Query[] getQuery(String[] initialArray) throws LocalDateParseException {
        if (initialArray == null) return null;
        if (!checkForEquals(initialArray.length, initialArray)) {
            log.info("exception");
            throw new IllegalArgumentException();
        }
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
            for (String dataColName : getAllDataColumns()) {
                try {
                    if (dataColName.equals(nameColumnItem)) {
                        DateParserInterface dateParserInterface = string -> {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            return LocalDate.parse(string.trim(), formatter);
                        };
                        itemParts[1] = dateParserInterface.getLocalDateFromString(itemParts[1]).toString();
                        break;
                    }
                } catch (DateTimeParseException e) {
                    throw new LocalDateParseException(e.getMessage());
                }
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

    /**
     * in this method we get all columns with date type from the database entities
     * @return array of column names
     */
    private String[] getAllDataColumns() {
        List<String> resultArray = new ArrayList<>();
            for (Column column : SaleTable.Cols.COLUMNS) {
                if (column.getColumnType().equals("date")) {
                    resultArray.add(column.getColumnName());
                }
            }
            for (Column column : ClientTable.Cols.COLUMNS) {
                if (column.getColumnType().equals("date")) {
                    resultArray.add(column.getColumnName());
                }
            }
            for (Column column : DeviceTable.Cols.COLUMNS) {
                if (column.getColumnType().equals("date")) {
                    resultArray.add(column.getColumnName());
                }
            }
        String[] strings = new String[resultArray.size()];
        strings = resultArray.toArray(strings);
        return strings;
    }
}