package com.imbirev.nikolay.services;

import java.sql.ResultSet;

// interface define some methods which are so important for dbService classes
public interface DbServiceInterface {

    public void createTable();

    public void dropTable();

    public void deleteFromTable(int id);

    public ResultSet getFromTable();


}
