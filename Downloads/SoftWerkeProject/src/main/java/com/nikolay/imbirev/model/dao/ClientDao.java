package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.nikolay.imbirev.model.executors.Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends AbstractDao {

    AbstractExecutor abstractExecutor;
    private Client mClient;
    private List<Client> clients;


    public ClientDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        clients = new ArrayList<>();

    }
    /**
     * this method return item from the database
     * @param tableName - from this table
     * @param array - with this conditions
     * @return new client or throw nullPointerException if we have no client for this conditions
     */
    public Client getItemFromTable(String tableName, Query[] array) {
        StringBuilder query = new StringBuilder()
                .append("select * from ").append(tableName).append(" where ");
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
            } else {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("' and ");
            }
        }
    abstractExecutor.execQuery(query.toString(), new Handler<Client>() {
        @Override
        public Client handle(ResultSet resultSet) throws SQLException {
            if (resultSet.next()) {
                Client client = new Client.ClientBuilder().setClientId(resultSet.getString(ClientTable.Cols.ID))
                        .setFirstName(resultSet.getString(ClientTable.Cols.FIRST_NAME))
                        .setLastName(resultSet.getString(ClientTable.Cols.SECOND_NAME))
                        .setDateofBirth(resultSet.getDate(ClientTable.Cols.DATE_OF_BIRTH).toLocalDate())
                        .build();
                mClient = client;
                return mClient;
            } else {
                return null;
            }
        }
    });
        return mClient;
    }
    public List<Client> getListFromTable(String tableName, Query[] array, Column[] sortArray) {
        StringBuilder query = new StringBuilder()
                .append("select * from ").append(tableName);
        if (array.length != 0) {
            query.append(" where ");
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
                } else {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("' and ");
                }
            }
        }
        if (sortArray.length == 0) {
            query.append(";");
        }
        else {
            query.append(" order by ");
            for (int i = 0; i < sortArray.length; i++) {
                if (i == sortArray.length - 1) {
                    query.append(sortArray[i].getColumnName());
                } else {
                    query.append(sortArray[i].getColumnName()).append(", ");
                }
            }
        }
        abstractExecutor.execQuery(query.toString(), new Handler<List<Client>>() {
            @Override
            public List<Client> handle(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    Client client = new Client.ClientBuilder().setClientId(resultSet.getString(ClientTable.Cols.ID))
                            .setFirstName(resultSet.getString(ClientTable.Cols.FIRST_NAME))
                            .setLastName(resultSet.getString(ClientTable.Cols.SECOND_NAME))
                            .setDateofBirth(resultSet.getDate(ClientTable.Cols.DATE_OF_BIRTH).toLocalDate())
                            .build();
                    mClient = client;
                    clients.add(mClient);
                }
                return clients;
            }
        });
        return clients;
    }
    /**
     * this method update current item into database
     * @param tableName - name of table which will be modified
     * @param id - id of the item
     * @param array - set of chenges
     */
    public void updateTable(String tableName, String id, Query[] array) {
        StringBuilder query = new StringBuilder()
                .append("update ").append(tableName).append(" set ");
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < array.length; i++) {
            if (i == array.length-1) {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append(" ' ");
                break;
            }
            else {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append(" ', ");
            }
        }
        query.append(" where clientId = '").append(id).append("'");
        System.out.println(query.toString());
        abstractExecutor.execUpdate(query.toString());
    }

}
