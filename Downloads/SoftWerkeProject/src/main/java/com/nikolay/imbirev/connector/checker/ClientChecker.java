package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;

import java.util.List;

public class ClientChecker implements CheckerInterface<Client> {

    /**
     * this method will add new client to the table
     * create a table
     * insert into it
     * @param object is the new client to add
     */
    @Override
    public void addToTable(Client object) {

    }

    /**
     * this method get data/list from the table of clients
     * @param columns - conditions
     * @param sortConditions - sorted columns
     * @return
     */
    @Override
    public Client getFromTable(Query[] columns, Column[] sortConditions) {
        return null;
    }

    /**
     * this method delete table with parameter
     * @param tableName
     */
    @Override
    public void deleteTable(String tableName) {

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
