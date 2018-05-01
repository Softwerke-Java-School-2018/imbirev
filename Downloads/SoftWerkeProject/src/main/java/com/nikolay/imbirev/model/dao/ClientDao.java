package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.savers.SaverClients;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.nikolay.imbirev.model.executors.Handler;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends AbstractDao {

    private AbstractExecutor abstractExecutor;
    private List<Client> clients;

    public ClientDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        clients = new ArrayList<>();

    }
    public RequestCode getListFromTable(@NotNull String tableName, Query[] array, Column[] sortArray) {
        if (tableName == null || tableName.equals("")) return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder();
        query.append(execQueryOperation(tableName, array, sortArray));
        try {
            abstractExecutor.execQuery(query.toString(), new Handler() {
                @Override
                public void handle(ResultSet resultSet) throws SQLException {
                    while (resultSet.next()) {
                        clients.add(Client.builder().clietnId(resultSet.getString(ClientTable.Cols.ID))
                                .firstName(resultSet.getString(ClientTable.Cols.FIRST_NAME))
                                .lastName(resultSet.getString(ClientTable.Cols.SECOND_NAME))
                                .dateOfBirth(resultSet.getDate(ClientTable.Cols.DATE_OF_BIRTH).toLocalDate())
                                .build());
                    }
                    SaverClients.getInstance().setClients(clients);
                }
            });
            return RequestCode.SUCCESS;
        } catch (IllegalArgumentException e) {
            return RequestCode.EMPTY_SET;
        } catch (SQLSyntaxErrorException e) {
            return RequestCode.SQL_SYNTAX_ERROR;
        } catch (SQLException e) {
            return RequestCode.DATABASE_ERROR;
        }
    }
}
