package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;

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
    public void addToTable(Client object) {
        clientDbService.sendToTable(object);
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
}
