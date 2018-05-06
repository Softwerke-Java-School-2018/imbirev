package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.DeviceDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.logging.Logger;

public class DeviceDbService implements DbInterface {

    private DeviceDao dao;
    private final static Logger log = Logger.getAnonymousLogger();
    private final static String TAG = "DeviceDbService";

    public DeviceDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.info(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new DeviceDao(executor);
    }
    @Override
    public RequestCode createTable(Column[] array) {
        return dao.createTable(DeviceTable.TABLE_NAME, array);
    }

    @Override
    public RequestCode deleteFromTable(Query[] array) {
        return dao.deleteFromTable(DeviceTable.TABLE_NAME, array);
    }

    @Override
    public RequestCode updateTable(Query[] condArray, Query[] newArray) {
        return dao.updateTable(DeviceTable.TABLE_NAME, condArray, newArray);
    }

    @Override
    public RequestCode insertIntoTable(Query[] array) {
        return dao.insertIntoTable(array, DeviceTable.TABLE_NAME);
    }

    @Override
    public RequestCode getFromTable(Query[] array, Column[] sortArray) {
        return dao.getListFromTable(DeviceTable.TABLE_NAME, array, sortArray);
    }
}
