package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.entities.Device;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.ArrayList;
import java.util.List;


public class ClientDbService extends AbstractDbService {

    private ClientDao dao;
    private List<Client> clients;

    public ClientDbService() {
        AbstractExecutor executor = null;
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        dao = new ClientDao(executor);
        clients = new ArrayList<>();
    }

    /**
     * this method send query to database to add new value
     * @param client - is the value to add
     */
    public void sendToTable(Client client) throws IllegalArgumentException {
        dao.createTable(ClientTable.TABLE_NAME, ClientTable.Cols.columns);
//        dao.insertIntoTable(new String[] {
//                        client.getClietnId(),
//                        client.getFirstName(),
//                        client.getLastName(),
//                        client.getDateOfBirth().toString()}, ClientTable.Cols.columns, ClientTable.TABLE_NAME);
    }

    /**
     * this method get list of objects from the database
     * @param tableName - from where
     * @param array - with what conditions
     * @return new list or throw IllegalArgumentException
     */
    public Client getFromTable(String tableName, Query[] array) {
        try {
            return dao.getItemFromTable(tableName, array);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<Client> getList(Query[] array, Column[] arraySort) {
        try {
            return dao.getListFromTable(ClientTable.TABLE_NAME, array, arraySort);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }



}
