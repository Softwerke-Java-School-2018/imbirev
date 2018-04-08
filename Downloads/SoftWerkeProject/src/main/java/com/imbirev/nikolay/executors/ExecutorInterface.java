package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface ExecutorInterface {

    abstract Statement createStatement(Connection connection) throws SQLException;

    abstract int execUpdate(String query);
}
