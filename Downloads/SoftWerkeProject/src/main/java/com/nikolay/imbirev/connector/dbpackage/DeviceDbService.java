package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.DeviceDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Device;
import com.nikolay.imbirev.model.entities.DeviceTable;
import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.List;

public class DeviceDbService extends AbstractDbService {

    private DeviceDao dao;

    public DeviceDbService() {
        AbstractExecutor executor = new AbstractExecutor();
        dao = new DeviceDao(executor);
    }

    /**
     * this method send query to database to add new value
     * @param value - is the value to add
     */
    public void sendToTable(Device value) {
        dao.createTable(DeviceTable.TABLE_NAME, DeviceTable.Cols.columns);
        dao.insertIntoTable(new String[] {
                value.getDeviceId(),
                value.getModel(),
                value.getProducer(),
                value.getColor(),
                value.getType(),
                String.valueOf(value.getPrice()),
                value.getDateOfManufactoringStarted().toString()
        }, DeviceTable.Cols.columns, DeviceTable.TABLE_NAME);
    }
    /**
     * this method get list of objects from the database
     * @param tableName - from where
     * @param array - with what conditions
     * @param sortColumns - sort conditions
     * @return new list or throw IllegalArgumentException
     */
    public List<Device> getList(String tableName, Query[] array, Column[] sortColumns) {
        try {
            return dao.getListFromTable(tableName, array, sortColumns);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    public Device getDevice(String tableName, Query[] array) {
        try {
            return dao.getItemFromTable(tableName,array);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

}
