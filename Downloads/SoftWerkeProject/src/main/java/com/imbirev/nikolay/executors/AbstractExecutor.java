package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

abstract class AbstractExecutor {

    private Connection connection;

    /**
     *
     * @param connection - object of some params from database
     */
    AbstractExecutor(Connection connection) {
        this.connection = connection;
    }

    /**
     * this method create new statemtent for query
     * @param connection
     * @return
     * @throws SQLException
     */
    private Statement createStatement(Connection connection) throws SQLException {
        return createStatement(connection);
    }

    /**
     *
     * @param sqlQuery
     * get the query
     * @return if everything ok - complete query, commit, close the statement
     * @throws SQLException - if we catch this  - rollback, throw new Exception
     */
    int execUpdate(String sqlQuery) throws SQLException {
        try {
            connection.setAutoCommit(false);
            Statement statement = createStatement(connection);
            statement.execute(sqlQuery);
            connection.commit();
            statement.close();
            return 1;
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException("Database problem, please check your connection");
        }
    }
}
