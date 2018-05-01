package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.apache.log4j.Logger;

public class ClientDbService implements DbInterface {

    private ClientDao dao;
    private final static Logger log = Logger.getLogger(ClientDbService.class);
    private final static String TAG = "ClientDbService";

    public ClientDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new ClientDao(executor);
    }

    @Override
    public RequestCode createTable(String tableName, Column[] array) {
        return dao.createTable(tableName, array);
    }

    @Override
    public RequestCode dropTable(String tableName) {
        return dao.dropTable(tableName);
    }

    @Override
    public RequestCode deleteFromTable(String tableName, Query[] array) {
        return dao.deleteFromTable(tableName, array);
    }

    @Override
    public RequestCode updateTable(String tableName, Query[] condArray, Query[] newArray) {
        return dao.updateTable(tableName, condArray, newArray);
    }

    @Override
    public RequestCode insertIntoTable(String tableName, Query[] array) {
        return dao.insertIntoTable(array, tableName);
    }

    @Override
    public RequestCode getFromTable(String tableName, Query[] array, Column[] sortArray) {
        return dao.getListFromTable(tableName, array, sortArray);
    }
}
