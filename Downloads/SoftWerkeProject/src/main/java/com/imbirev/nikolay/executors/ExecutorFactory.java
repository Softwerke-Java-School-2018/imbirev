package com.imbirev.nikolay.executors;


import java.sql.Connection;

// фабрика по созданию executors
public class ExecutorFactory {

    private Connection connection;

    public ExecutorFactory(Connection connection) {
        this.connection = connection;
    }

    public  DeviceExecutor createDeviceExecutor() {
        return new DeviceExecutor(connection);
    }

    public  ClientExecutor createClientExecutor() {
        return new ClientExecutor(connection);
    }

    public SaleExecutor createSaleExecutor() {
        return new SaleExecutor(connection);
    }

}