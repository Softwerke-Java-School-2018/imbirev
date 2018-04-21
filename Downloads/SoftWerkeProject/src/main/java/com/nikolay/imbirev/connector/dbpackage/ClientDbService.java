package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.List;


public class ClientDbService extends AbstractDbService {

    private ClientDao dao;

    public ClientDbService() {
        AbstractExecutor executor = new AbstractExecutor();
        dao = new ClientDao(executor);
    }

    /**
     * this method send query to database to add new value
     * @param client - is the value to add
     */
    public void sendToTable(Client client) throws IllegalArgumentException {
        dao.createTable(ClientTable.TABLE_NAME, ClientTable.Cols.columns);
        dao.insertIntoTable(new String[] {
                        client.getClietnId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getDateOfBirth().toString()}, ClientTable.Cols.columns, ClientTable.TABLE_NAME);
    }

    /**
     * this method get list of objects from the database
     * @param tableName - from where
     * @param array - with what conditions
     * @param sortColumns - sort conditions
     * @return new list or throw IllegalArgumentException
     */
    public List<Client> getFromTable(String tableName, Query[] array, Column[] sortColumns) {
        try {
            return dao.getItemFromTable(tableName, array, sortColumns);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

}
