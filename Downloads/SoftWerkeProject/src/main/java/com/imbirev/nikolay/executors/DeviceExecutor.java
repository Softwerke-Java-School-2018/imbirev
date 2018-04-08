package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Device Executor to work with queries of changing device information
 */
public class DeviceExecutor extends AbstractExecutor implements QueriesInterface {

    DeviceExecutor(Connection connection) {
        super(connection);
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
    // TODO
    @Override
    public ResultSet getFromTableMethod(String query) throws SQLException {
        return null;
    }
}
