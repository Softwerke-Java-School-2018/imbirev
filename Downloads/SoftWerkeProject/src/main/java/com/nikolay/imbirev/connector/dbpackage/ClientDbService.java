package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.java.Log;

@Log
public class ClientDbService implements DbInterface {

    private ClientDao dao;
    private final static String TAG = "ClientDbService";

    public ClientDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.info(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new ClientDao(executor);
    }

    @Override
    public RequestCode createTable(Column[] array) {
        return dao.createTable(ClientTable.TABLE_NAME, array);
    }

    @Override
    public RequestCode deleteFromTable(Query[] array) {
        return dao.deleteFromTable(ClientTable.TABLE_NAME, array);
    }

    @Override
    public RequestCode updateTable(Query[] condArray, Query[] newArray) {
        return dao.updateTable(ClientTable.TABLE_NAME, condArray, newArray);
    }

    @Override
    public RequestCode insertIntoTable(Query[] array) {
        return dao.insertIntoTable(array, ClientTable.TABLE_NAME);
    }

    @Override
    public RequestCode getFromTable(Query[] array, Column[] sortArray) {
        return dao.getListFromTable(ClientTable.TABLE_NAME, array, sortArray);
    }
}
