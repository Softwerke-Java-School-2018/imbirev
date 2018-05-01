package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.RequestCode;

public interface DbInterface {

    RequestCode createTable(Column[] array);

    RequestCode dropTable();

    RequestCode deleteFromTable(Query[] array);

    RequestCode updateTable(Query[] condArray, Query[] newArray);

    RequestCode insertIntoTable(Query[] array);

    RequestCode getFromTable(Query[] array, Column[] sortArray);
}
