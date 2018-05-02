package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.savers.SaleSaver;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.sun.istack.internal.NotNull;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class SaleDao extends AbstractDao {

    private AbstractExecutor abstractExecutor;
    private List<Sale> sales;

    public SaleDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        sales = new ArrayList<>();
    }

    /**
     * this method try to find some data from the database
     * @param tableName is the table where we will try find data (can not be null)
     * @param array is query conditions for search information
     * @param sortArray is an array of sorting columns to determine the order of the results
     * @return success code and add list of sales to the singleton or return unsuccessful code
     */
    public RequestCode getListFromTable(@NotNull String tableName, Query[] array, Column[] sortArray) {
        if (tableName == null || tableName.equals("")) return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder();
        query.append(execQueryOperation(tableName, array, sortArray));
        try {abstractExecutor.execQuery(query.toString(), resultSet -> {
                while (resultSet.next()) {
                    Sale sale = Sale.builder()
                            .saleId(resultSet.getString(SaleTable.Cols.ID))
                            .clientId(resultSet.getString(ClientTable.Cols.FIRST_NAME) + " " + resultSet.getString(ClientTable.Cols.SECOND_NAME))
                            .dateOfSale(resultSet.getDate(SaleTable.Cols.DATE_OF_SALE).toLocalDate())
                            .overallPrice(resultSet.getDouble(SaleTable.Cols.PRICE))
                            .build();
                    sales.add(sale);
                }
        });
            SaleSaver.getInstance().setSaleList(sales);
            return RequestCode.SUCCESS;
        } catch(IllegalArgumentException e) {
            return RequestCode.EMPTY_SET;
        } catch(SQLSyntaxErrorException e){
            return RequestCode.SQL_SYNTAX_ERROR;
        } catch(SQLException e){
            return RequestCode.DATABASE_ERROR;
        }
    }

    /**
     * in this method we get data from sale_table (we have inner join here to connect to another table to get client info
     */
    StringBuilder execQueryOperation(@NotNull String tableName, Query[] array, Column[] sortArray) {
        StringBuilder query = new StringBuilder();
        query.append("select * from ").append(tableName).append(" inner join ").append(ClientTable.TABLE_NAME).append(" on ");
        query.append(SaleTable.TABLE_NAME).append(".").append(SaleTable.Cols.ClIENT_ID).append(" = ");
        query.append(ClientTable.TABLE_NAME).append(".").append(ClientTable.Cols.ID);
        if (array != null && array.length != 0) {
            query.append(" where ");
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    query.append(SaleTable.TABLE_NAME).append(".").append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
                } else {
                    query.append(SaleTable.TABLE_NAME).append(".").append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("' and ");
                }
            }
        }
        if (sortArray != null && sortArray.length != 0) {
            query.append(" order by ");
            for (int i = 0; i < sortArray.length; i++) {
                if (i == sortArray.length - 1) {
                    query.append(SaleTable.TABLE_NAME).append(".").append(sortArray[i].getColumnName());
                } else {
                    query.append(SaleTable.TABLE_NAME).append(".").append(sortArray[i].getColumnName()).append(", ");
                }
            }
        }
        else {
            query.append(";");
        }
        return query;
    }
}