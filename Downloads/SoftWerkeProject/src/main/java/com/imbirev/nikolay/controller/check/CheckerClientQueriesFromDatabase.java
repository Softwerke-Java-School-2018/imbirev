package com.imbirev.nikolay.controller.check;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.services.ClientDbService;
import com.imbirev.nikolay.model.services.ServiceFactory;

import java.util.List;

public class CheckerClientQueriesFromDatabase implements CheckerInterface<Client> {

    private ClientDbService dbService;
    private ServiceFactory factory;
    private List<Client> clients;

    CheckerClientQueriesFromDatabase() {
        factory = new ServiceFactory();
        dbService = factory.createClientDbService();
    }

    @Override
    public void insertIntoTableQuery(Client client) {
        dbService.createTable();
        dbService.insertIntoTable(client);
    }

    @Override
    public void deleteFromTableQuery(int id) {
        dbService.deleteFromTable(id);
    }

    @Override
    public void dropTableQuery() {
        dbService.dropTable();
    }

    @Override
    public List<Client> getClientsFromTable() {
        clients = dbService.getFromTable();
        return clients;
    }
}
