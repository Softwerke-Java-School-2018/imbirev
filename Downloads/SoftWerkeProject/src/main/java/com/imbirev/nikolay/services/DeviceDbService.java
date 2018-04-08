package com.imbirev.nikolay.services;

import com.imbirev.nikolay.beans.Device;
import com.imbirev.nikolay.executors.DeviceDao;
import com.imbirev.nikolay.executors.DeviceExecutor;
import com.imbirev.nikolay.executors.ExecutorFactory;
import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;
import java.sql.ResultSet;

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
    public ResultSet getFromTable() {
        return null;
    }
}
