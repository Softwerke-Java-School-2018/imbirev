package com.nikolay.imbirev.model.dao;

import com.mysql.cj.core.util.StringUtils;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.SQLWarningException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j;

@Log4j
public class AbstractDao implements DaoInterface {

    private AbstractExecutor abstractExecutor;
    private static final String WHERE = " where ";
    private static final String AND = "' and ";
    private static final String EQUAL = " = '";

    AbstractDao(AbstractExecutor executor) {
         abstractExecutor = executor;
         log.info("Executor is created");
     }

    /**
     * this method create table
     * @param tableName is a new table name
     * @param array is array of columns with types and notnull/null, auto/non autoincrement flags
     */
     public RequestCode createTable(String tableName, Column[] array) {
         if (StringUtils.isNullOrEmpty(tableName) || array == null || array.length < 2) {
             return RequestCode.SYNTAX_ERROR;
         }
         else {
             StringBuilder query = new StringBuilder()
                     .append("create table if not exists ")
                     .append(tableName).append(" (");
                try {
                    for (int i = 0; i < array.length; i++) {
                            if (i == array.length - 1) {
                                query.append(getColumn(array[i]));
                            } else {
                                query.append(getColumn(array[i])).append(" , ");
                            }
                    }
                } catch (IllegalArgumentException e) {
                    return RequestCode.SYNTAX_ERROR;
                }
             query.append(", primary key (").append(array[0].getColumnName()).append("));");
             return execOperation(query);
         }
     }

     private StringBuilder getColumn(Column column) {
         if (column == null) {
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


     // this method do inserting into table

    /**
     * h
     * @param array columns
     * @param tableName is name of table
     */
     public RequestCode insertIntoTable(Query[] array, String tableName) {
         if (array == null || tableName == null || array.length == 0 || tableName.equals(""))
             return RequestCode.SYNTAX_ERROR;
         StringBuilder query = new StringBuilder()
                 .append("insert into ").append(tableName).append(" ")
                 .append("(");
         for (int y = 0; y < array.length; y++) {
             if (array.length == 1) {
                 query.append(array[y].getColumnName());
             }
             if (y == array.length-1) {
                 query.append(array[y].getColumnName());
             } else {
                 query.append(array[y].getColumnName()).append(", ");
             }
         }
         query.append(") values (");
         for (int y = 0; y < array.length; y++) {
             if (array.length == 1) {
                 query.append("'").append(array[y].getColumnQuery()).append("'");
             }
             if (y == array.length-1) {
                 query.append("'").append(array[y].getColumnQuery()).append("'");
             } else {
                 query.append("'").append(array[y].getColumnQuery()).append("', ");
             }
         }
         query.append(");");
         return execOperation(query);
     }

    /**
     * this method delete string from table where it is necessary
     * @param tableName - table of deleting
     * @param array - 1 parameter - columnName, 2 parameter - is a condition of deleting
     */
     @Override
     public RequestCode deleteFromTable(String tableName, Query[] array) {
         if (tableName == null || tableName.equals(""))
             return RequestCode.SYNTAX_ERROR;
         StringBuilder query = new StringBuilder()
                 .append("delete from ").append(tableName);
         getWhereClause(array, query);
         log.info(query.toString());
         return execOperation(query);
     }

    private RequestCode execOperation(StringBuilder query) {
        try {
            log.info(query.toString());
            abstractExecutor.execUpdate(query.toString());
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
     * test handler method, only for test cleaning
     * @param tableName is a test tables
     * @return success
     */
    public RequestCode dropTable(String tableName) {
         StringBuilder builder = new StringBuilder();
         builder.append("drop table ").append(tableName);
         return execOperation(builder);
    }


    /**
     * this method update current item into database
     * @param tableName - name of table which will be modified
     * @param array - set of changes
     */
    public RequestCode updateTable(String tableName, Query[] arrayConditions,Query[] array) {
        if (tableName == null || array == null || array.length==0 || tableName.equals(""))
            return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder()
                .append("update ").append(tableName).append(" set ");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length-1) {
                query.append(array[i].getColumnName()).append(EQUAL).append(array[i].getColumnQuery()).append("' ");
                break;
            }
            else {
                query.append(array[i].getColumnName()).append(EQUAL).append(array[i].getColumnQuery()).append("', ");
            }
        }
        getWhereClause(arrayConditions, query);
        return execOperation(query);
    }

    StringBuilder execQueryOperation(String tableName, Query[] array, Column[] sortArray) {
        StringBuilder query = new StringBuilder();
        query.append("select * from ").append(tableName);
        getWhereClause(array, query);
        if (sortArray != null && sortArray.length != 0) {
            query.append(" order by ");
            for (int i = 0; i < sortArray.length; i++) {
                if (i == sortArray.length - 1) {
                    query.append(sortArray[i].getColumnName());
                } else {
                    query.append(sortArray[i].getColumnName()).append(", ");
                }
            }
        }
        return query;
    }

    private void getWhereClause(Query[] array, StringBuilder query) {
        if (array != null && array.length != 0) {
            query.append(WHERE);
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    query.append(array[i].getColumnName()).append(EQUAL).append(array[i].getColumnQuery()).append("'");
                } else {
                    query.append(array[i].getColumnName()).append(EQUAL).append(array[i].getColumnQuery()).append(AND);
                }
            }
        }
    }
}