package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.beans.ClientBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Client Executor to work with queries of changing client information
 */
public class ClientExecutor extends AbstractExecutor implements QueriesInterface {


    private Connection connection;
    private List<Client> clientList;


    protected ClientExecutor(Connection connection) {
        super(connection);
        this.connection = connection;
        clientList = new ArrayList<>();
    }

    // there some methods, which take only 1 param - query and try to complete it
    @Override
    public int createTableMethod(String query) throws SQLException {
            return execUpdate(query);
    }

    @Override
    public int insertIntoTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }


    @Override
    public int deleteMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int deleteTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    // here is another situation, with ResultSet
    @Override
    public List<Client> getFromTableMethod(String query) throws SQLException {
        ResultSet resultSet = createStatement(connection).getResultSet();
        while (resultSet.next()) {
            ClientBuilder builder = new ClientBuilder(0);
            builder.addOrChangeClientId(resultSet.getInt(1));
            builder.addOrChangeFirstName(resultSet.getString(2));
            builder.addOrChangeLastName(resultSet.getString(3));
            builder.addOrChangeLocalDate(resultSet.getDate(4));
            clientList.add(new Client(builder));
        }
        return clientList;
    }
}
