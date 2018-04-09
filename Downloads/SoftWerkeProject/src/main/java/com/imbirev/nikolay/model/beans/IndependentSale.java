package com.imbirev.nikolay.model.beans;

import java.time.LocalDate;

public class IndependentSale {

    private int saleId;
    private int deviceId;
    private int clientId;
    private int numberOfDevices;
    private LocalDate saleDate;

    // для того чтобы записывать в бд покупку
    public IndependentSale(IndependentSaleBuilder builder) {
        this.deviceId = builder.getDeviceId();
        this.saleId = builder.getSaleId();
        this.numberOfDevices = builder.getNumberOfDevices();
        this.clientId = builder.getClientId();
        this.saleDate = builder.getSaleDate();
    }

    public int getSaleId() {
        return saleId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }
}
