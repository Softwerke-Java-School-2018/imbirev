package com.imbirev.nikolay.controller.sort;

import com.imbirev.nikolay.model.beans.IndependentSale;
import sun.awt.datatransfer.DataTransferer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// this class sort list of sales by using Collections.sort
public class SaleSortClass implements SortInterface<IndependentSale> {

    // initial list
    private List<IndependentSale> sales;

    SaleSortClass(List<IndependentSale> sales) {
        this.sales = sales;
    }

    /**
     * this method returns sorted initial list
     * @param sortParameter is a definer which how we will sort list
     * @return
     */
    @Override
    public List<IndependentSale> sortByAlphabet(String sortParameter) {
       switch (sortParameter) {
           case "device id": Collections.sort(sales, new Comparator<IndependentSale>() {
               @Override
               public int compare(IndependentSale o1, IndependentSale o2) {
                   return Integer.compare(o1.getDeviceId(), (o2.getDeviceId()));
               }
           });
           return sales;
           case "client id": Collections.sort(sales, new Comparator<IndependentSale>() {
               @Override
               public int compare(IndependentSale o1, IndependentSale o2) {
                   return Integer.compare(o1.getClientId(), (o2.getClientId()));
               }
           });
               return sales;
           case "number of devices":
               Collections.sort(sales, new Comparator<IndependentSale>() {
                   @Override
                   public int compare(IndependentSale o1, IndependentSale o2) {
                       return Integer.compare(o1.getNumberOfDevices(), (o2.getNumberOfDevices()));
                   }
               });
               return sales;
               default:return sales;
       }
    }

    /**
     * this method returns list sorted by date
     * @return
     */
    @Override
    public List<IndependentSale> sortByDate() {
        Collections.sort(sales, new Comparator<IndependentSale>() {
            @Override
            public int compare(IndependentSale o1, IndependentSale o2) {
                return o1.getSaleDate().compareTo(o2.getSaleDate());
            }
        });
        return sales;
    }

}
