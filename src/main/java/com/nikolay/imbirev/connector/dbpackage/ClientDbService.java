package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.connector.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class ClientDbService implements DbInterface {

    private static final String TAG = "ClientDbService";
    private ClientDao dao;

    private ClientDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new ClientDao(executor);
    }

    public static ClientDbService getClientDbService() throws DatabaseAccessException {
        return new ClientDbService();
    }

    @Override
    public RequestCode createTable(List<Column> array) {
        return dao.createTable(ClientTable.TABLE_NAME, array.toArray(new Column[0]));
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