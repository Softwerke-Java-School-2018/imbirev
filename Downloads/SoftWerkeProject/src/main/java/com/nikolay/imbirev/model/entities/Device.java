package com.nikolay.imbirev.model.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Device {

    private String deviceId;

    private String model;
    private String producer;

    private double price;
    private String type;
    private String color;

    private LocalDate dateOfManufactoringStarted;

}

