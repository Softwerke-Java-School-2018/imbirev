package com.imbirev.nikolay.beans;

import java.time.LocalDate;

public class IndependentSale {

    private int saleId;
    private int deviceId;
    private int clientId;
    private int numberOfDevices;
    private LocalDate saleDate;

    // для того чтобы записывать в бд покупку
    public IndependentSale(int saleId, int deviceId, int numberOfDevices, int clientId, LocalDate saleDate) {
        this.deviceId = deviceId;
        this.saleId = saleId;
        this.numberOfDevices = numberOfDevices;
        this.clientId = clientId;
        this.saleDate = saleDate;
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
