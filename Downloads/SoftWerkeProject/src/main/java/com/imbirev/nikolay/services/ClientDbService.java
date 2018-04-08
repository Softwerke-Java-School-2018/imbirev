package com.imbirev.nikolay.services;

import com.imbirev.nikolay.beans.Client;
import com.imbirev.nikolay.executors.ClientDao;
import com.imbirev.nikolay.executors.ClientExecutor;
import com.imbirev.nikolay.executors.ExecutorFactory;
import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;
import java.sql.ResultSet;

// here the class for Client connection
public class ClientDbService implements DbServiceInterface {

    // necessary field of database information
    private Connection connection;
    private ClientExecutor executor;
    ClientDao clientDao;

    // here we connect to the connection in the sigleton
    ClientDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
        ExecutorFactory factory = new ExecutorFactory(connection);
        executor = factory.createClientExecutor();
        clientDao = new ClientDao(executor);
    }

    @Override
    public void createTable() {
        clientDao.createTable();
    }

    @Override
    public void dropTable() {
        clientDao.dropTable();
    }

    public void insertIntoTable(Client client) {
        clientDao.insertIntoTable(client);
    }

    @Override
    public void deleteFromTable(int id) {
        clientDao.deleteFromTable(id);
    }

    @Override
    public ResultSet getFromTable() {
        return null;
    }
}
