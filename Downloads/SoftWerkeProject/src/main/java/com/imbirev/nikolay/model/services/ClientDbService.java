package com.imbirev.nikolay.model.services;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.executors.ClientDao;
import com.imbirev.nikolay.model.executors.ClientExecutor;
import com.imbirev.nikolay.model.executors.ExecutorFactory;
import com.imbirev.nikolay.model.singletons.ServiceSingleton;

import java.sql.Connection;
import java.util.List;

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
    public List<Client> getFromTable() {
        return clientDao.getFromTable();
    }
}
