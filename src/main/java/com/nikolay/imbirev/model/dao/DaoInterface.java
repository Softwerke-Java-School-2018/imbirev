package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.model.entities.*;

public interface DaoInterface {

    RequestCode createTable(String tableName, Column[] array);

    RequestCode insertIntoTable(Query[] array, String tableName);

    RequestCode deleteFromTable(String tableName, Query[] array);

    RequestCode updateTable(String tableName, Query[] arrayConditions, Query[] arrayNewData);
}