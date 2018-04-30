package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.SaleSaver;
import com.nikolay.imbirev.connector.checker.Query;
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
    public RequestCode getListFromTable(@NotNull String tableName, Query[] array, Column[] sortArray) {
        if (tableName == null || tableName.equals("")) return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder();
        query.append(execQueryOperation(tableName, array, sortArray));
        try {abstractExecutor.execQuery(query.toString(), resultSet -> {
                while (resultSet.next()) {
                    Sale sale = Sale.builder()
                            .saleId(resultSet.getString(SaleTable.Cols.ID))
                            .clientId(resultSet.getString(SaleTable.Cols.ClIENT_ID))
                            .dateOfSale(resultSet.getDate(SaleTable.Cols.DATE_OF_SALE).toLocalDate())
                            .overallPrice(resultSet.getDouble(SaleTable.Cols.PRICE))
                            .build();
                    sales.add(sale);
                }
            SaleSaver.getInstance().setSaleList(sales);
        });
            return RequestCode.SUCCESS;
        }
        catch(IllegalArgumentException e) {
            return RequestCode.EMPTY_SET;
        } catch(SQLSyntaxErrorException e){
            return RequestCode.SQL_SYNTAX_ERROR;
        } catch(SQLException e){
            return RequestCode.DATABASE_ERROR;
        }
    }
}