package com.imbirev.nikolay.controller.search;

public interface SearchInterface<T> {

    public abstract T searchByParam(String param, String keyOfSearch);

}
