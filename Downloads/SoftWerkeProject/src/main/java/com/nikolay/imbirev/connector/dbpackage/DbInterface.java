package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.entities.Query;

public interface DbInterface<T> {

    void modifyTable(String tableName ,String columnName);

    void modifyForeignKey(String tableName ,String columnName, String tableRef, String columnRef);

    void dropTable(String tableName);

    void deleteFromTable(String tableName ,Query[] array);

    void updateTable(String tableName, String id, Query[] array);
}
