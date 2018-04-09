package com.imbirev.nikolay.controller.search;

import com.imbirev.nikolay.model.beans.IndependentSale;

import java.time.LocalDate;
import java.util.List;

import static com.imbirev.nikolay.controller.search.ClientSearch.rank;

public class SaleSearch implements SearchInterface<IndependentSale> {

    // list of searched object
    private List<IndependentSale> saleList;

    public SaleSearch(List<IndependentSale> list) {
        this.saleList = list;
    }

    /**
     *
     * @param param is the parameter of search
     * @param keyOfSearch is the searching object
     * @return sale from sale or null
     */
    @Override
    public IndependentSale searchByParam(String param, String keyOfSearch) {
        if (param.equals("Date of sale")) {
            LocalDate[] array = new LocalDate[saleList.size()];
            for (int i = 0; i < array.length; i++) array[i] = saleList.get(i).getSaleDate();
            int position = rank(keyOfSearch, array, 0, array.length);
            if (position >= 0) return saleList.get(position);
            else return null;
        } else throw new IllegalArgumentException();
    }
}
