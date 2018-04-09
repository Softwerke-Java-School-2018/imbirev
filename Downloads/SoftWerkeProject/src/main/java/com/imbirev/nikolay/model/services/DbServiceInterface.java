package com.imbirev.nikolay.model.services;

import java.sql.ResultSet;
import java.util.List;

// interface define some methods which are so important for dbService classes
public interface DbServiceInterface<T> {

    // here we create table
    public void createTable();

    // here we drop it
    public void dropTable();

    // here we delete strings from table with id = id
    public void deleteFromTable(int id);

    // here we get data from database by using List
    public List<T> getFromTable();


}
