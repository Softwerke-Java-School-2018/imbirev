package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.model.entities.Column;

import java.util.List;

public interface CheckerInterface<T> {

    void addToTable(T object);

    void deleteFromTable(T object);

    T getFromTable(String[] cols, String[] vals);

    void deleteTable(String tableName);

    List<Command> help();
}
