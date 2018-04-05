package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceExecutor extends AbstractExecutor implements QueriesInterface {


    /**
     * конструктор экзекьютора
     *
     * @param connection
     */
    DeviceExecutor(Connection connection) {
        super(connection);
    }

    @Override
    public int createTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int insertIntoTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int updateTableMethod(String query) throws SQLException {
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

    // TODO
    @Override
    public ResultSet getFromTableMethod(String query) throws SQLException {
        return null;
    }
}
