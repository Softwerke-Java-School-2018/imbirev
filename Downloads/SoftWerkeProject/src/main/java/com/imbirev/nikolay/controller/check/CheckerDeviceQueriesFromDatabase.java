package com.imbirev.nikolay.controller.check;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.beans.Device;

import java.util.List;

public class CheckerDeviceQueriesFromDatabase implements CheckerInterface<Device> {
    @Override
    public void insertIntoTableQuery(Device object) {
        
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
