package com.nikolay.imbirev.model.entities;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Query {

    private String ColumnName;
    private String ColumnQuery;

}