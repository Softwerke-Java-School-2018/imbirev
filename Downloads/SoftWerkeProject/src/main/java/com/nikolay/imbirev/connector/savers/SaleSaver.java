package com.nikolay.imbirev.connector.savers;

import com.nikolay.imbirev.model.entities.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleSaver {

    private static SaleSaver ourInstance = new SaleSaver();
    private List<Sale> saleList;

    public static SaleSaver getInstance() {
        return ourInstance;
    }

    private SaleSaver() {
        saleList = new ArrayList<>();
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}
