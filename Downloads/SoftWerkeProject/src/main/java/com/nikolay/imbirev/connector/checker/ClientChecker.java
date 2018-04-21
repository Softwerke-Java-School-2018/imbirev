package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;

import java.util.List;

public class ClientChecker implements CheckerInterface<Client> {


    private ClientDbService clientDbService;

    public ClientChecker() {
        clientDbService = new ClientDbService();
    }

    /**
     * this method will add new client to the table
     * create a table
     * insert into it
     * @param object is the new client to add
     */
    @Override
    public void addToTable(Client object) throws IllegalArgumentException {
        clientDbService.sendToTable(object);
    }

    /**
     * this method deletes object from the database
     * @param object
     */
    @Override
    public void deleteFromTable(Client object) throws IllegalArgumentException {
        clientDbService.deleteFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.FIRST_NAME, object.getFirstName()),
                new Query(ClientTable.Cols.SECOND_NAME, object.getLastName()),
                new Query(ClientTable.Cols.DATE_OF_BIRTH, object.getDateOfBirth().toString())
        });
    }

    /**
     * this method get data/list from the table of clients
     * @param columns - conditions
     * @param sortConditions - sorted columns
     * @return client or throw new IllegalArgumentException if we have more than 1 client in list
     */
    @Override
    public Client getFromTable(Query[] columns, Column[] sortConditions) throws IllegalArgumentException {
        List<Client> clients = clientDbService.getFromTable(ClientTable.TABLE_NAME, columns, sortConditions);
        if (clients.size() > 1) {
            System.out.println("ClientChecker");
            throw new IllegalArgumentException();
        }
        else {
            return clients.get(0);
        }
    }

    public List<Client> getClientList(Query[] columns, Column[] sortConditions) throws IllegalArgumentException {
        return clientDbService.getFromTable(ClientTable.TABLE_NAME, columns, sortConditions);
    }

    /**
     * this method delete table with parameter
     * @param tableName
     */
    @Override
    public void deleteTable(String tableName) {
        clientDbService.dropTable(tableName);
    }

    /**
     * this method return list of commands to enter into the console to do some actions
     * @return
     */
    @Override
    public List<Command> help() {
        return CommandHolder.getCommandHolder().getCommandList();
    }

    public void updateClient(Client client, String[] columns, String[] newData) throws IllegalArgumentException {
        Client client1 = getFromTable(new Query[]{
                new Query(ClientTable.Cols.FIRST_NAME, client.getFirstName()),
                new Query(ClientTable.Cols.SECOND_NAME, client.getLastName())
        }, new Column[]{});
        if (client1 == null) {
            throw new IllegalArgumentException();
        }
        else {
            Query[] queries = new Query[columns.length];
            if (queries.length == 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < columns.length; i++) {
                if (columns[i].trim().equals("name")) {
                    queries[i] = new Query(ClientTable.Cols.FIRST_NAME, newData[i]);
                }
                if (columns[i].trim().equals("surname")) {
                    queries[i] = new Query(ClientTable.Cols.SECOND_NAME, newData[i]);
                }
                if (columns[i].trim().equals("date of birth")) {
                    queries[i] = new Query(ClientTable.Cols.DATE_OF_BIRTH, newData[i]);
                }
            }
            clientDbService.updateTable(ClientTable.TABLE_NAME, client1.getClietnId(), queries);
        }
    }
}
