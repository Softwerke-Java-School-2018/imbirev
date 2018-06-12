package com.nikolay.imbirev.model.executors;

import com.nikolay.imbirev.connector.exceptions.SQLWarningException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface ExecutorInterface {

    Statement createStatement(Connection connection);

    void execUpdate(String query) throws SQLWarningException, SQLException;

    void execQuery(String query, Handler result) throws SQLException;

}
