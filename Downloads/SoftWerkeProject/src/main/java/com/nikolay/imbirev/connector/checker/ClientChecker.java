package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClientChecker implements CheckerInterface<Client> {


    private ClientDbService clientDbService;
    private Query[] queries;

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
     * this method get data from the table of clients
     * @param columns - conditions
     * @return client or throw new IllegalArgumentException if we have more than 1 client in list
     */
    @Override
    public Client getFromTable(String[] columns, String[] values) throws IllegalArgumentException {
        queries = getQueryArray(columns, values);
        return clientDbService.getFromTable(ClientTable.TABLE_NAME, queries);

    }

//    public List<Client> getClientList(Query[] columns, Column[] sortConditions) throws IllegalArgumentException {
//        return clientDbService.getFromTable(ClientTable.TABLE_NAME, columns, sortConditions);
//    }

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
        Client client1 = getFromTable(new String[]{"name", "surname"}, new String[]{client.getFirstName(), client.getLastName()});
        queries = getQueryArray(columns, newData);
        if (client1 == null) {
            throw new IllegalArgumentException();
        }
        else {

            clientDbService.updateTable(ClientTable.TABLE_NAME, ClientTable.Cols.ID, client1.getClietnId(), queries);
        }
    }
    private Query[] getQueryArray(String[] columns, String[] newData) {
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
                queries[i] = new Query(ClientTable.Cols.DATE_OF_BIRTH, Date.valueOf(LocalDate.parse(newData[i], DateTimeFormatter.ofPattern("d/MM/yyyy"))).toString());
            }
        }
        return queries;
    }
//    private Column[] getSortColumns(String[] cols) {
//        if (cols.length == 0) return new Column[] {};
//        Column[] columns = new Column[cols.length];
//        for (int i = 0; i < columns.length; i++) {
//            if (cols[i].trim().equals("name")) {
//                columns[i] = new Column(ClientTable.Cols.FIRST_NAME, null, false, false);
//            }
//            if (cols[i].trim().equals("surname")) {
//                columns[i] = new Column(ClientTable.Cols.SECOND_NAME, null, false, false);
//            }
//            if (cols[i].trim().equals("date of birth")) {
//                columns[i] = new Column(ClientTable.Cols.DATE_OF_BIRTH, null, false, false);
//            }
//        }
//        return columns;
//    }

//    public List<Client> getListOfClient(String[] cols, String[] data, String[] sortCols) {
//        if (cols.length > 0) {
//            System.out.println(clientDbService.getList(getQueryArray(cols, data), getSortColumns(sortCols)).size()  + "   checker");
//            return clientDbService.getList(getQueryArray(cols, data), getSortColumns(sortCols));
//        } else {
//            return clientDbService.getList(new Query[]{}, getSortColumns(sortCols));
//        }
//    }
}
