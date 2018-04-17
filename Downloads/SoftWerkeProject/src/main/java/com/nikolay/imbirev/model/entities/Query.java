package com.nikolay.imbirev.model.entities;

public class Query {

    private String ColumnName;
    private String ColumnQuery;

    public Query(String columnName, String columnQuery) {
        this.ColumnName = columnName;
        this.ColumnQuery = columnQuery;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getColumnQuery() {
        return ColumnQuery;
    }

    public void setColumnQuery(String columnQuery) {
        ColumnQuery = columnQuery;
    }
}