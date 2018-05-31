package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.DeviceDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class DeviceDbService implements DbInterface {

    private DeviceDao dao;
    private static final String TAG = "DeviceDbService";

    private DeviceDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new DeviceDao(executor);
    }

    public static DeviceDbService getDeviceDbService() throws DatabaseAccessException {
        return new DeviceDbService();
    }

    @Override
    public RequestCode createTable(List<Column> array) {
        return dao.createTable(DeviceTable.TABLE_NAME, (Column[]) array.toArray());
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
