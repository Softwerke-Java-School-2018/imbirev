package com.nikolay.imbirev.model.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;


@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Sale {

    private String saleId;

    private String clientId;

    private double overallPrice;

    private LocalDate dateOfSale;

}
