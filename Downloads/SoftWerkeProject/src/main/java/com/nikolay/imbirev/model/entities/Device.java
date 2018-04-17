package com.nikolay.imbirev.model.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Device {

    private String deviceId;

    private String model;
    private String producer;

    private double price;

    private LocalDate dateOfManufactoringStarted;


    public Device(String deviceId, String model, String producer, double price, LocalDate dateOfManufactoringStarted) {
        this.deviceId = deviceId;
        this.model = model;
        this.producer = producer;
        this.price = price;
        this.dateOfManufactoringStarted = dateOfManufactoringStarted;
    }

    public static class DeviceBuilder {

        private String deviceId;

        private String model;
        private String producer;

        private double price;
        private LocalDate dateOfManufactoringStarted;

        public DeviceBuilder setDeviceId() {
            UUID id = UUID.randomUUID();
            StringBuilder deviceId = new StringBuilder().append("dev").append(id.toString());
            this.deviceId = deviceId.toString();
            return this;
        }
        public DeviceBuilder setDeviceId(String id) {
            this.deviceId = id;
            return this;
        }
        public DeviceBuilder setModel(String model) {
            this.model = model;
            return this;
        }
        public DeviceBuilder setProducer(String producer) {
            this.producer = producer;
            return this;
        }
        public DeviceBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public DeviceBuilder setDate(LocalDate dateOfManufactoringStarted) {
            this.dateOfManufactoringStarted = dateOfManufactoringStarted;
            return this;
        }

        public Device build() {
            return new Device(deviceId, model, producer, price, dateOfManufactoringStarted);
        }


    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getModel() {
        return model;
    }

    public String getProducer() {
        return producer;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfManufactoringStarted() {
        return dateOfManufactoringStarted;
    }
}

