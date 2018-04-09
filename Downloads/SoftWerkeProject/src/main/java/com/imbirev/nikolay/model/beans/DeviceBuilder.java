package com.imbirev.nikolay.model.beans;

/*
this class creates object device with all obligatory fields
 */

import java.time.LocalDate;

public class DeviceBuilder {

    // here some necessary fields
    private int deviceId;

    private String model;
    private String manufacturer;
    private String color;
    private String type;

    private LocalDate dateOfManufacturingStarted;

    private double price;

    // this is obligatory field, cause for complete device we have to fill all fields here
    private int size;

    // standart constructor
    public DeviceBuilder(int size) {
        this.size = size;
    }

    // here some methods to add information to device object

    // there are some methods to add components of new device below
    // each component add 1 to the device size
    // each method @param - is the console data

    public void addOrChangeDeviceId(int deviceId) {
        this.deviceId = deviceId;
        size++;
    }
    public void addOrChangeDeviceModel(String model) {
        this.model = model;
        size++;
    }
    public void addOrChangeDeviceManufactorer(String manufacturer) {
        this.manufacturer = manufacturer;
        size++;
    }
    public void addOrChangeDeviceColor(String color) {
        this.color = color;
        size++;
    }
    public void addOrChangeDeviceType(String type) {
        this.type = type;
        size++;
    }
    public void addOrChangeDeviceDateOfManufactoring(LocalDate date) {
        this.dateOfManufacturingStarted = date;
        size++;
    }
    public void addOrChangeDevicePrice(double price) {
        this.price = price;
        size++;
    }


    // here some get methods for variables


    public int getDeviceId() {
        return deviceId;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfManufacturingStarted() {
        return dateOfManufacturingStarted;
    }

    public int getSize() {
        return size;
    }
}
