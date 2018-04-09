package com.imbirev.nikolay.controller.sort;

import com.imbirev.nikolay.model.beans.Client;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// this class is sort class for client objects
public class ClientSortClass implements SortInterface<Client> {

    // enter variable
    private List<Client> sortList;

    ClientSortClass(List<Client> sortList) {
        this.sortList = sortList;
    }

    /**
     * this method return sorted list by Collections.sort
     * @param sortParameter is a definer for what attribute we will sort list
     * @return sorted list
     */
    @Override
    public List<Client> sortByAlphabet(String sortParameter) {
        if (sortParameter.equals("First Name")) {
            Collections.sort(sortList, new Comparator<Client>() {
                @Override
                public int compare(Client o1, Client o2) {
                    return o1.getfName().compareTo(o2.getfName());
                }
            });
            return sortList;
        }
        else {
            Collections.sort(sortList, new Comparator<Client>() {
                @Override
                public int compare(Client o1, Client o2) {
                    return o1.getlName().compareTo(o2.getlName());
                }
            });
            return sortList;
        }
    }


    /**
     * this method return sorted list by Collections.sort
     * @return sorted list
     */
    @Override
    public List<Client> sortByDate() {
            Collections.sort(sortList, new Comparator<Client>() {
                @Override
                public int compare(Client o1, Client o2) {
                    return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
                }
            });
            return sortList;
    }
}
