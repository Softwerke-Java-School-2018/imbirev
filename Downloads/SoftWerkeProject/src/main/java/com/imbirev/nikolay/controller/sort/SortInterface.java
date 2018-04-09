package com.imbirev.nikolay.controller.sort;

import java.time.LocalDate;
import java.util.List;

/*
this interface define some basic methods for different sorts
 */
public interface SortInterface<T> {

    public abstract List<T> sortByAlphabet(String sortParameter);


    public abstract List<T> sortByDate();

}
