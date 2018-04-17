package com.nikolay.imbirev.model.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Sale {

    private String saleId;

    private String clientId;

    private double overallPrice;

    private LocalDate dateOfSale;

    public Sale(String saleId, String clientId, double overallPrice, LocalDate dateOfSale) {
        this.saleId = saleId;
        this.clientId = clientId;
        this.overallPrice = overallPrice;
        this.dateOfSale = dateOfSale;
    }

    public static class SaleBuilder {

        private String saleId;

        private String clientId;

        private double overallPrice;

        private LocalDate dateOfSale;


        public SaleBuilder setSaleId(String id) {
            this.saleId = id;
            return this;
        }
        public SaleBuilder setSaleId() {
            UUID id = UUID.randomUUID();
            StringBuilder saleId = new StringBuilder().append("sal").append(id.toString());
            this.saleId = saleId.toString();
            return this;
        }

        public SaleBuilder setClient(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public SaleBuilder setPrice(double overallPrice) {
            this.overallPrice = overallPrice;
            return this;
        }
        public SaleBuilder setDateOfSale(LocalDate dateOfSale) {
            this.dateOfSale = dateOfSale;
            return this;
        }

    }


    public String getSaleId() {
        return saleId;
    }

    public String getClientid() {
        return clientId;
    }

    public double getOverallPrice() {
        return overallPrice;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }
}
