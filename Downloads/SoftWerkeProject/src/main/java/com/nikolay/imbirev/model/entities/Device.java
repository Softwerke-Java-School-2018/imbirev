package com.nikolay.imbirev.model.entities;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Device extends BaseEntity {

    private String deviceId;

    private String model;
    private String producer;

    private double price;
    private String type;
    private String color;

    private LocalDate dateOfManufactoringStarted;

}

