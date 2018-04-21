package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.nikolay.imbirev.model.executors.Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDao extends AbstractDao {

    AbstractExecutor abstractExecutor;
    private List<Sale> sales;


    public SaleDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        sales = new ArrayList<>();
    }
    /**
     * this method return item from the database
     * @param tableName - from this table
     * @param array - with this conditions
     * @return new client or throw nullPointerException if we have no client for this conditions
     */
    public List<Sale> getItemFromTable(String tableName, Query[] array, Column[] sortArray) {
        StringBuilder query = new StringBuilder()
                .append("select * from ").append(tableName).append(" where ");
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < array.length; i++) {
            if (i == array.length-1) {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
            }
            else {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("', ");
            }
        }
        if (sortArray.length == 0) {
            query.append(");");
        }
        else {
            query.append(" order by ");
            for (int i = 0; i < sortArray.length; i++) {
                if (i == sortArray.length-1) {
                    query.append(sortArray[i]);
                }
                else {
                    query.append(sortArray[i]).append(", ");
                }
            }
            query.append(");");
        }
        abstractExecutor.execQuery(query.toString(), new Handler<List<Sale>>() {
            @Override
            public List<Sale> handle(ResultSet resultSet) throws SQLException {
                resultSet.next();
                String id = resultSet.getString(1);
                if(id.substring(0, 2).equals("sal")) {
                    while (resultSet.next()) {
                        Sale sale = new Sale.SaleBuilder()
                                .setSaleId(id)
                                .setClient(resultSet.getString(SaleTable.Cols.ClIENT_ID))
                                .setDateOfSale(resultSet.getDate(SaleTable.Cols.DATE_OF_SALE).toLocalDate())
                                .setPrice(resultSet.getDouble(SaleTable.Cols.PRICE))
                                .build();
                        sales.add(sale);
                    }
                }
                return sales;
            }
        });
        throw new NullPointerException();
    }
}
