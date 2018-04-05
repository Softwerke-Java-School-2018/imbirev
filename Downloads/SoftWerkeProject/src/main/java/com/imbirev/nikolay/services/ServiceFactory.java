package com.imbirev.nikolay.services;


// фабрика по созданию нужных подключений
public class ServiceFactory {

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
