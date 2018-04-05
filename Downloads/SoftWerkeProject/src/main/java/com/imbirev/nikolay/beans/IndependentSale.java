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

    // для того чтобы дергать объект из бд
    public IndependentSale(int deviceId, int numberOfDevices, int clientId, LocalDate date) {
        this(0, deviceId, numberOfDevices, clientId, date);
    }

    // сеттеры и геттеры

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(int numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
