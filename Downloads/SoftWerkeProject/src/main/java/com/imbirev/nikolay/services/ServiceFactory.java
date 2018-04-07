package com.imbirev.nikolay.services;


// here is the factory of creating new connections
public class ServiceFactory {

    // these methods below connect to  connection to database for each object

    public ClientDbService createClientDbService() {
        return new ClientDbService();
    }

    public SaleDbService createSaleDbService() {
        return new SaleDbService();
    }

    public DeviceDbService createDeviceDbService() {
        return new DeviceDbService();
    }
}
