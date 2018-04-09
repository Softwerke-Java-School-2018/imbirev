package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.beans.IndependentSale;
import com.imbirev.nikolay.model.beans.IndependentSaleBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sale Executor to work with queries of changing sale information
 */
public class SaleExecutor extends AbstractExecutor implements QueriesInterface {

    private Connection connection;
    private List<IndependentSale> saleList;

    SaleExecutor(Connection connection) {
        super(connection);
        this.connection = connection;
        saleList = new ArrayList<>();
    }


    // there some methods, which take only 1 param - query and try to complete it
    @Override
    public int createTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int insertIntoTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int deleteMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int deleteTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    // here is another situation, with ResultSet
    @Override
    public List<IndependentSale> getFromTableMethod(String query) throws SQLException {
        ResultSet resultSet = createStatement(connection).getResultSet();
        while (resultSet.next()) {
            IndependentSaleBuilder builder = new IndependentSaleBuilder(0);
            builder.addOrChangeSaleId(resultSet.getInt(1));
            builder.addOrChangeDeviceId(resultSet.getInt(2));
            builder.addOrChangeClientId(resultSet.getInt(3));
            builder.addOrChangeNumberOfDevices(resultSet.getInt(4));
            builder.addOrChangeSaleDate(resultSet.getDate(5).toLocalDate());
            saleList.add(new IndependentSale(builder));
        }
        return saleList;
    }
}
