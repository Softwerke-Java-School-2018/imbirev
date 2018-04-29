package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.connector.checker.Query;

public interface DbInterface<T> {


    void dropTable(String tableName);

    void deleteFromTable(String tableName ,Query[] array);

    void updateTable(String tableName,String col,  String id, Query[] array);
}
