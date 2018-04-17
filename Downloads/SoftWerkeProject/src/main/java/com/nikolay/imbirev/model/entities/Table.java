package com.nikolay.imbirev.model.entities;

// this class describe table entity
public class Table {

    private final String tableName;

    private final Column[] columns;

    Table(String tableName, Column[] columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public Column[] getColumns() {
        return columns;
    }
}
