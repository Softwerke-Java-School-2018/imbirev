package com.nikolay.imbirev.model.dao;


import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.RequestCode;

// standard interface which is determine all methods for abstract dao
public interface DaoInterface {

    RequestCode createTable(String tableName, Column[] array);

    RequestCode insertIntoTable(Query[] array, String tableName);

    RequestCode deleteFromTable(String tableName, Query[] array);

    RequestCode updateTable(String tableName, Query[] arrayConditions, Query[] arrayNewData);
}
