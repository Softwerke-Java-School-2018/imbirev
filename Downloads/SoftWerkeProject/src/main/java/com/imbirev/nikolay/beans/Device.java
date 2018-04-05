package com.imbirev.nikolay.beans;


// класс для описания сущности девайса
public class Device {

    private int deviceId;
    private String model;
    private String manufacturer;
    private String color;
    private String type;
    private double price;


    // конструктор для того, чтобы из бд дергать
    public Device(String model, String manufacturer, String color, String type, double price) {
        this(0, model, manufacturer, color, type, price);
    }

    // конструктор для добавления в бд девайса
    public Device(int id, String model, String manufacturer, String color, String type, double price) {
        deviceId = id;
        this.color = color;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.type = type;
    }
    // далее перечислены сеттеры и геттеры

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
