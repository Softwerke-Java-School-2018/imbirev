package com.imbirev.nikolay.beans;


import java.time.LocalDate;

// entity of device
public class Device {

    // obligatory fields for device
    private int deviceId;


    private String model;
    private String manufacturer;
    private String color;
    private String type;


    private double price;


    private LocalDate dateOfStartingManufactoring;

    // constructor for adding device object to database or getting from there

    /**
     *
     * @param builder - is the builder of device, which can be used to add/change information from the console/database
     */
    public Device(DeviceBuilder builder) {
        this.deviceId = builder.getDeviceId();
        this.color = builder.getColor();
        this.manufacturer = builder.getManufacturer();
        this.model = builder.getModel();
        this.price = builder.getPrice();
        this.type = builder.getType();
        this.dateOfStartingManufactoring = builder.getDateOfManufacturingStarted();
    }
}
