package com.imbirev.nikolay.executors;


import java.sql.Connection;

// this is factory for creating new Executors
public class ExecutorFactory {

    // necessary variable to connect to database
    private Connection connection;

    public ExecutorFactory(Connection connection) {
        this.connection = connection;
    }

    /**
     * method creates new DeviceExecutor
     * @return
     */
    public  DeviceExecutor createDeviceExecutor() {
        return new DeviceExecutor(connection);
    }

    /**
     * method creates new ClientExecutor
     * @return
     */
    public  ClientExecutor createClientExecutor() {
        return new ClientExecutor(connection);
    }

    /**
     * method creates new SaleExecutor
     * @return
     */
    public SaleExecutor createSaleExecutor() {
        return new SaleExecutor(connection);
    }

}