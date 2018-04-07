package com.imbirev.nikolay.beans;

import java.time.LocalDate;

// pattern builder for independentSale with some methods for create client parameters

public class IndependentSaleBuilder {

    // here some obligatory fields

    private int saleId;
    private int deviceId;
    private int clientId;
    private int numberOfDevices;

    private LocalDate saleDate;

    // standart size of new sale is 5 (four obligatory fields)
    private int size;

    /**
     * standart constructor
     * @param size
     */
    public IndependentSaleBuilder(int size) {
        this.size = size;
    }

    // there are some methods to add components of new sale below
    // each component add 1 to the sale size
    // each method param - is the console data

    public void addOrChangeSaleId(int saleId) {
        this.saleId = saleId;
        size++;
    }

    public void addOrChangeDeviceId(int deviceId) {
        this.deviceId = deviceId;
        size++;
    }

    public void addOrChangeClientId(int clientId) {
        this.clientId = clientId;
        size++;
    }

    public void addOrChangeNumberOfDevices(int numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
        size++;
    }

    // here is some get methods for client variables


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

    public int getSize() {
        return size;
    }
}
