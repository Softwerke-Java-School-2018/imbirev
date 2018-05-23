package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.LocalDateParseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Log4j
public class QueryForm {

    private Column[] sortColumns;
    private Query[] searchQueries;
    private Query[] insertOrUpdateQueries;

    private static final String DATE_TYPE = "date";
    private static final String EQUAL = "=";

    /*
    method has args only for testing
     */
   public String createQuery(String operation, String entity, String[] sortArray, String[] searchArray, String[] insertOrUpdateArray) {
        log.info("operation " + operation);
        log.info("entity " + entity);
        log.info("sortArray " + Arrays.toString(sortArray));
        log.info("searchArray " + Arrays.toString(searchArray));
        log.info("insertOrUpdateArray " + Arrays.toString(insertOrUpdateArray));
        if (!checkForAvailableOperation(operation, searchArray, insertOrUpdateArray)) return RequestCode.DATA_ERROR.toString();
        try {
            sortColumns = getSortColumns(sortArray);
            searchQueries = getQuery(searchArray);
            insertOrUpdateQueries = getQuery(insertOrUpdateArray);
        } catch (IllegalArgumentException e) {
            log.error("error data");
            return RequestCode.DATA_ERROR.toString();
        } catch (LocalDateParseException e) {
            log.error("date parse exception");
            return RequestCode.DATE_PARSING_ERROR.toString();
        }
        return performOperation(operation, entity).toString();
    }

    /**
     * here we delegate continuous operation to each entity performer
     * @return request of them operation
     */
    private RequestCode performOperation(String operation, String entity) {
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
    private boolean checkForAvailableOperation(String operation, String[] searchArray, String[] insertOrUpdateArray) {
        switch (operation.trim()) {
            case "create":
            case "delete":
                return insertOrUpdateArray.length != 0;
            case "update":
                return searchArray.length != 0 && insertOrUpdateArray.length != 0;
            case "get":
                return insertOrUpdateArray.length == 0;
            default:
                return false;
        }
    }

    /**
     * here we perform new array of columns if we want to sort query
     * @return new array of columns or empty array (if initial array is null || have 0 length) or throw IllegalArgumentException
     * if we have illegal columns here
     */
    private Column[] getSortColumns(String[] sortArray) {
        if (sortArray == null || sortArray.length == 0) {
            log.warn("empty sort part");
            return new Column[0];
        }
        Column[] resultArray = new Column[sortArray.length];
        int counter = 0;
        for (int i = 0; i < sortArray.length; i++) {
            String queryName = sortArray[i].trim();
            for (Column tableName : getAllTablesColumns()) {
                if (tableName.getColumnName().equals(queryName))  {
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
    private Column[] getAllTablesColumns() {
        List<Column> resultArray = new ArrayList<>();
        resultArray.addAll(Arrays.asList(SaleTable.Cols.getCOLUMNS()));
        resultArray.addAll(Arrays.asList(ClientTable.Cols.getCOLUMNS()));
        resultArray.addAll(Arrays.asList(DeviceTable.Cols.getCOLUMNS()));
        Column[] columns = new Column[resultArray.size()];
        columns = resultArray.toArray(columns);
        return columns;
    }

    /**
     * here we perform query of arguments (if we have column with data -> try to convert it to the LocalDate)
     * @param initialArray from this.string array
     * @return new query array or throw IllegalArgumentException (or empty array, if initialArray is empty)
     * or throw LocalDateParseException if we cannot convert input to LocalDate
     */
    private Query[] getQuery(String[] initialArray) throws LocalDateParseException {
        if (initialArray.length == 0) {
            log.warn("empty query");
            return new Query[0];
        }
        if (!checkForEquals(initialArray.length, initialArray)) {
            log.error("'=' illegal amount");
            throw new IllegalArgumentException();
        }
        int num = 0;
        Query[] resultArray = new Query[initialArray.length];
        for (String item : initialArray) {
            String[] itemParts = item.split(EQUAL);
            if (num >= resultArray.length) {
                log.error("illegal amount of parts");
                throw new IllegalArgumentException();
            }
            String nameColumnItem = itemParts[0].trim();
            String queryItem = itemParts[1].trim();
            resultArray[num++] = performQuery(nameColumnItem, queryItem);
        }
        return resultArray;
    }

    /**
     * in this method we perform new Query to the database
     * @param nameColumnItem is the left part of the (some_column = some_query) expression
     * @param queryItem is the right part of the expression
     * @return new Query with column bundled with query
     * @throws LocalDateParseException if we cannot parse date
     */
    private Query performQuery(String nameColumnItem, String queryItem) throws LocalDateParseException {
        int counter = 0;
        for (Column colName : getAllTablesColumns()) {
            if (colName.getColumnName().equals(nameColumnItem)) {
                counter++; // if we have this column in entities -> increment counter
                try {
                    if (colName.getColumnType().trim().equals(DATE_TYPE)) {
                        DateParserInterface dateParserInterface = this::getDate;
                        queryItem = dateParserInterface.getLocalDateFromString(queryItem).toString();
                        break;
                    }
                } catch (DateTimeParseException e) {
                    log.error("illegal date format");
                    throw new LocalDateParseException(e.getMessage());
                }
            }
        }
        if (counter == 0) throw new IllegalArgumentException(); // we have illegal column
        return new Query(nameColumnItem, queryItem);
    }

    private LocalDate getDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(string.trim(), formatter);
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