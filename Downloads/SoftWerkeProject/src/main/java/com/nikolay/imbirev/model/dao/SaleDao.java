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
                            .saleId(String.valueOf(resultSet.getInt(SaleTable.Cols.ID)))
                            .clientName(resultSet.getString(SaleTable.Cols.CLIENT_NAME))
                            .clientSurname(resultSet.getString(SaleTable.Cols.CLIENT_SURNAME))
                            .dateOfSale(resultSet.getDate(SaleTable.Cols.DATE_OF_SALE).toLocalDate())
                            .overallPrice(Double.parseDouble(resultSet.getString(SaleTable.Cols.PRICE)))
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
}