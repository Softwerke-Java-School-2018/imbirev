package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.RequestCode;

public interface DbInterface {

    RequestCode createTable(String tableName, Column[] array);

    RequestCode dropTable(String tableName);

    RequestCode deleteFromTable(String tableName ,Query[] array);

    RequestCode updateTable(String tableName, Query[] condArray, Query[] newArray);

    RequestCode insertIntoTable(String tableName, Query[] array);

    RequestCode getFromTable(String tableName, Query[] array, Column[] sortArray);
}
