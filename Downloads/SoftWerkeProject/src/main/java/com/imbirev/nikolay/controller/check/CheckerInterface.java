package com.imbirev.nikolay.controller.check;


import com.imbirev.nikolay.model.beans.Client;

import java.util.List;

public interface CheckerInterface<T> {


    public abstract void insertIntoTableQuery(T object);

    public abstract void deleteFromTableQuery(int id);

    public abstract void dropTableQuery();

    public abstract List<Client> getClientsFromTable();

}
