package com.imbirev.nikolay.services;

import com.imbirev.nikolay.beans.IndependentSale;
import com.imbirev.nikolay.executors.ExecutorFactory;
import com.imbirev.nikolay.executors.SaleDao;
import com.imbirev.nikolay.executors.SaleExecutor;
import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;
import java.sql.ResultSet;

// here the class for Sale connection
public class SaleDbService implements DbServiceInterface {

    // necessary field of database information
    private Connection connection;
    private SaleExecutor executor;
    private SaleDao saleDao;
    private ExecutorFactory factory;

    // here we connect to the connection in the singleton
    SaleDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
        factory = new ExecutorFactory(connection);
        executor = factory.createSaleExecutor();
        saleDao = new SaleDao(executor);
    }

    @Override
    public void createTable() {
        saleDao.createTable();
    }

    @Override
    public void dropTable() {
        saleDao.dropTable();
    }

    @Override
    public void deleteFromTable(int id) {
        saleDao.deleteFromTable(id);
    }

    public void insertIntoTable(IndependentSale sale) {
        saleDao.insertIntoTable(sale);
    }

    @Override
    public ResultSet getFromTable() {
        return null;
    }
}
