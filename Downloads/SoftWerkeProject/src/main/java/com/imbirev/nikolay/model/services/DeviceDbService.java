package com.imbirev.nikolay.model.services;

import com.imbirev.nikolay.model.beans.Device;
import com.imbirev.nikolay.model.executors.DeviceDao;
import com.imbirev.nikolay.model.executors.DeviceExecutor;
import com.imbirev.nikolay.model.executors.ExecutorFactory;
import com.imbirev.nikolay.model.singletons.ServiceSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

// here the class for Sale connection
public class DeviceDbService implements DbServiceInterface{

    // necessary field of database information
    private Connection connection;
    private ExecutorFactory factory;
    private DeviceExecutor executor;
    private DeviceDao deviceDao;

    // here we connect to the connection in the singleton
    DeviceDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
        factory = new ExecutorFactory(connection);
        executor = factory.createDeviceExecutor();
        deviceDao = new DeviceDao(executor);
    }

    @Override
    public void createTable() {
        deviceDao.createTable();
    }

    @Override
    public void dropTable() {
        deviceDao.dropTable();
    }

    @Override
    public void deleteFromTable(int id) {
        deviceDao.deleteFromTable(id);
    }

    public void insertIntoTable(Device device) {
        deviceDao.insertIntoTable(device);
    }

    @Override
    public List<Device> getFromTable() {
        return deviceDao.getFromTable();
    }
}
