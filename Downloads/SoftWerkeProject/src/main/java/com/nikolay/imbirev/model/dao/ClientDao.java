package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.savers.SaverClients;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ClientDao extends AbstractDao {

    private AbstractExecutor abstractExecutor;
    private List<Client> clients;

    public ClientDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        clients = new ArrayList<>();

    }

    /**
     * this method try to find some data from the database
     * @param tableName is the table where we will try find data (can not be null)
     * @param array is query conditions for search information
     * @param sortArray is an array of sorting columns to determine the order of the results
     * @return success code and add list of clients to the singleton or return unsuccessful code
     */
    public RequestCode getListFromTable(@NonNull String tableName, Query[] array, Column[] sortArray) {
        if (tableName.equals("")) return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder();
        query.append(execQueryOperation(tableName, array, sortArray));
        log.info(query.toString());
        try {
            abstractExecutor.execQuery(query.toString(), resultSet -> {
                while (resultSet.next()) {
                    clients.add(Client.builder().clietnId(String.valueOf(resultSet.getInt(ClientTable.Cols.ID)))
                            .firstName(resultSet.getString(ClientTable.Cols.FIRST_NAME))
                            .lastName(resultSet.getString(ClientTable.Cols.SECOND_NAME))
                            .dateOfBirth(resultSet.getDate(ClientTable.Cols.DATE_OF_BIRTH).toLocalDate())
                            .build());
                }
            });
            SaverClients.getInstance().setClients(clients);
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
