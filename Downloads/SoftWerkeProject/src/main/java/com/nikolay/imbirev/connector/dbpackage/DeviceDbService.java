package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.DeviceDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Device;
import com.nikolay.imbirev.model.entities.DeviceTable;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.List;

public class DeviceDbService extends AbstractDbService {

    private DeviceDao dao;

    public DeviceDbService() {
        AbstractExecutor executor = new AbstractExecutor();
        dao = new DeviceDao(executor);
    }

    public void sendToTable(Device value) {
        dao.createTable(DeviceTable.TABLE_NAME, DeviceTable.Cols.columns);
        dao.insertIntoTable(new String[] {
                value.getDeviceId(),
                value.getModel(),
                value.getProducer(),
                String.valueOf(value.getPrice()),
                value.getDateOfManufactoringStarted().toString()
        }, DeviceTable.Cols.columns, DeviceTable.TABLE_NAME);
    }
    public List<Device> getFromTable(String tableName, Query[] array, Column[] sortColumns) {
        try {
            return dao.getListFromTable(tableName, array, sortColumns);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

}
