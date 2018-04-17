package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;

import java.util.List;

public interface CheckerInterface<T> {

    void addToTable(T object);

    T getFromTable(Query[] columns, Column[] sortConditions);

    void deleteTable(String tableName);

    List<Command> help();
}
