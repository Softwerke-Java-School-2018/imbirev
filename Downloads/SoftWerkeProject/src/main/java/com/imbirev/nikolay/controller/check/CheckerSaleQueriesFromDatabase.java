package com.imbirev.nikolay.controller.check;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.beans.IndependentSale;

import java.util.List;

public class CheckerSaleQueriesFromDatabase implements CheckerInterface<IndependentSale> {
    @Override
    public void insertIntoTableQuery(IndependentSale object) {

    }

    @Override
    public void deleteFromTableQuery(int id) {

    }

    @Override
    public void dropTableQuery() {

    }

    @Override
    public List<Client> getClientsFromTable() {
        return null;
    }
}
