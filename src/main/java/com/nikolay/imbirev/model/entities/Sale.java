package com.nikolay.imbirev.model.entities;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "saleId") // call super = false
public class Sale extends BaseEntity {

    private String saleId;

    private String clientName;

    private String clientSurname;

    private double overallPrice;

    private LocalDate dateOfSale;

}