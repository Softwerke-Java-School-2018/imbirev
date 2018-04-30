package com.nikolay.imbirev.connector;

import com.nikolay.imbirev.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class SaverClients {

    private static SaverClients ourInstance = new SaverClients();

    private List<Client> clients;

    public static SaverClients getInstance() {
        return ourInstance;
    }

    private SaverClients() {
        clients = new ArrayList<>();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
