package com.nikolay.imbirev.model.dao;

import com.mysql.cj.core.util.StringUtils;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.connector.exceptions.SQLWarningException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import java.sql.SQLException;
import java.util.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.ArrayUtils;

@Log4j
public class AbstractDao implements DaoInterface {

    private static final String COMMA = ", ";
    private static final String SPECIAL_COMMA = "', ";
    private static final String TAG = "'";
    private AbstractExecutor abstractExecutor;
    private static final String WHERE = " where ";
    private static final String AND = "' and ";
    private static final String EQUAL = " = '";

    AbstractDao(AbstractExecutor executor) {
         abstractExecutor = executor;
         log.info("Executor is created");
     }

    /**
     * creating table is going in 3 stages:
     * 1 - checking all inputs for null or empty (we cannot create table with 1 column)
     * 2 - if every check passed - start with native sql
     * 3 - using stream to create new columns
     * @param tableName - is a new table name
     * @param array - array of new columns with params (see class column)
     * @return result of operation or syntax error if smt went wrong
     */
     public RequestCode createTable(String tableName, Column[] array) {
         if (StringUtils.isNullOrEmpty(tableName) || ArrayUtils.isEmpty(array) || array.length < 2) {
             return RequestCode.SYNTAX_ERROR;
         }
         else {
             StringBuilder builder = new StringBuilder()
                     .append("create table if not exists ")
                     .append(tableName).append(" (");
                try {
                    Arrays.stream(array).limit((long) array.length-1).forEach(column -> builder.append(getColumn(column).append(COMMA)));
                    builder.append(getColumn(array[array.length-1]));
                } catch (IllegalArgumentException e) {
                    return RequestCode.SYNTAX_ERROR;
                }
             builder.append(", primary key (").append(array[0].getColumnName()).append("));");
             return execOperation(builder);
         }
     }

    /**
     * it is obligatory method for creating table to parse object to string builder by some params
     * @param column is a initial object (see class column)
     * @return string builder with column description for creating table or throw IllegalArgumentException if the column is null
     * result is logged
     */
     private StringBuilder getColumn(Column column) {
         if (Objects.isNull(column)) {
             log.error("column is null");
             throw new IllegalArgumentException();
         }
         StringBuilder builder = new StringBuilder();
         builder.append(column.getColumnName()).append(" ")
                 .append(column.getColumnType()).append(" ")
                 .append(column.isNullableColumn() ? " " : "not null").append(" ")
                 .append(column.isAutoIncremented() ? "auto_increment" : " ");
         log.info(builder.toString());
         return builder;
     }

    /**
     * insert into table goes in three stages too:
     * 1 - checks ability (if smt went wrong - return syntax request code)
     * 2 - construct the query using stream (we special comma/comma to add "'" symbol
     * after the each query or to divide column easily)
     * 3 - if we have last query - we should not use comma
     * @param array is an array of query (name -> column, colQuery -> sql query)
     * @param tableName is a table where we will insert
     * @return result of operation or syntax error
     */
     public RequestCode insertIntoTable(Query[] array, String tableName) {
         if (!Objects.isNull(array) && !StringUtils.isNullOrEmpty(tableName) && ArrayUtils.isNotEmpty(array)) {
             Query lastQuery = array[array.length - 1];
             StringBuilder builder = new StringBuilder("insert into ");
             builder.append(tableName).append(" (");
             Arrays.stream(array).limit((long) array.length - 1).forEach(query -> builder.append(query.getColumnName()).append(COMMA));
             builder.append(lastQuery.getColumnName()).append(") values (");
             Arrays.stream(array).limit((long) array.length - 1).forEach(query -> builder.append(TAG).append(query.getColumnQuery()).append(SPECIAL_COMMA));
             builder.append(TAG).append(lastQuery.getColumnQuery()).append("')");
             return execOperation(builder);
         } else {
             return RequestCode.SYNTAX_ERROR;
         }
     }

    /**
     * firstly we need to check tableName to emptiness or null
     * then we go to whereMethod where we construnct query (WARNING: also we can have no where clauses,
     * but we delete all rows in table)
     * @param tableName id a table name where we will delete smt
     * @param array is an array of where clauses
     * @return result of operation or syntax error if smt went wrong
     */
     @Override
     public RequestCode deleteFromTable(String tableName, Query[] array) {
         if (StringUtils.isNullOrEmpty(tableName))
             return RequestCode.SYNTAX_ERROR;
         StringBuilder builder = new StringBuilder()
                 .append("delete from ").append(tableName);
         getWhereClause(array, builder);
         log.info(builder.toString());
         return execOperation(builder);
     }

    /**
     * we try to execute query to database, and we have several scenarios: see RequestCode enum class
     * @param builder is a query to database
     * @return request code of operation
     */
    private RequestCode execOperation(StringBuilder builder) {
        try {
            log.info(builder.toString());
            abstractExecutor.execUpdate(builder.toString());
            return RequestCode.SUCCESS;
        } catch (SQLWarningException e) {
            log.warn(e.getMessage());
            return RequestCode.WARNING;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return RequestCode.DATABASE_ERROR;
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return RequestCode.SQL_SYNTAX_ERROR;
        }
    }

    /**
     * here we drop all table by tableName (method is used only in test part)
     */
    public RequestCode dropTable(String tableName) {
         StringBuilder builder = new StringBuilder();
         builder.append("drop table ").append(tableName);
         return execOperation(builder);
    }

    /**
     * table is updating in three stages too:
     * 1 - standart check for ability (return syntax error if smt went wrong)
     * 2 - construct query to database step by step using stream
     * we also use special comma to add "'" symbol in query
     * when we have last element - we add only close tag "'"
     * @param tableName is a table where we will update row
     * @param arrayConditions - is an array of where clauses
     * @param array - is an array of new row
     * @return result of operation or syntax error
     */
    public RequestCode updateTable(String tableName, Query[] arrayConditions,Query[] array) {
        if (!Objects.isNull(array) && ArrayUtils.isNotEmpty(array) && !StringUtils.isNullOrEmpty(tableName)) {
            StringBuilder builder = new StringBuilder()
                    .append("update ").append(tableName).append(" set ");
            Arrays.stream(array).limit((long) array.length - 1).
                    forEach(currentQuery -> builder.append(currentQuery.getColumnName()).append(EQUAL)
                            .append(currentQuery.getColumnQuery()).append(SPECIAL_COMMA));
            builder.append(array[array.length - 1].getColumnName()).append(EQUAL)
                    .append(array[array.length - 1].getColumnQuery()).append(TAG);
            getWhereClause(arrayConditions, builder);
            return execOperation(builder);
        } else {
            return RequestCode.SYNTAX_ERROR;
        }
    }

    /**
     * default visible for current classes which are children of this class
     * we construct query for select easily step by step by using streams
     * where clauses is getting from special method see getWhereClause method
     * and sort columns is getting from stream dividing by comma
     * if we have last sort column - don't use comma
     * @param tableName - is a table for query
     * @param array - is a where clauses
     * @param sortArray - is a sort array
     * @return result of operation
     */
    StringBuilder execQueryOperation(String tableName, Query[] array, Column[] sortArray) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from ").append(tableName);
        getWhereClause(array, builder);
        if (!Objects.isNull(sortArray) && ArrayUtils.isNotEmpty(sortArray)) {
            builder.append(" order by ");
            Arrays.stream(sortArray).limit((long) sortArray.length-1).forEach(column -> builder.append(column.getColumnName()).append(COMMA));
            log.info(builder);
            builder.append(sortArray[sortArray.length-1].getColumnName());
        }
        return builder;
    }

    /**
     * this method allow us to make where clause for everything you need
     * firstly we check for ability or empty (in some cases we have no where clauses so we do nothing)
     * using stream we append builder by where clauses
     * (columnName -> sql column name, columnQuery -> sql column where clause) divided by and key word
     * when we see last element -> add tag and end
     * @param array is an array of where clauses
     * @param builder is a query builder in the case which we need
     */
    private void getWhereClause(Query[] array, StringBuilder builder) {
        if (!Objects.isNull(array) && ArrayUtils.isNotEmpty(array)) {
            builder.append(WHERE);
            Arrays.stream(array).limit((long) array.length - 1).forEach(query -> builder.append(query.getColumnName()).append(EQUAL).append(query.getColumnQuery()).append(AND));
            builder.append(array[array.length-1].getColumnName()).append(EQUAL).append(array[array.length-1].getColumnQuery()).append(TAG);
        }
    }
}