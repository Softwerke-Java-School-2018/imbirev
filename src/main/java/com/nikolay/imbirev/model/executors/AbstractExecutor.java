package com.nikolay.imbirev.model.executors;

import com.nikolay.imbirev.model.connections.ConnectionSingleton;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.exceptions.SQLWarningException;
import lombok.extern.log4j.Log4j;

import java.sql.*;

@Log4j
public class AbstractExecutor implements ExecutorInterface {

    private Connection connection;

    private AbstractExecutor() throws DatabaseAccessException {
        try {
            connection = ConnectionSingleton.getConnection().getMySQlConnection();
        } catch (SQLInvalidAuthorizationSpecException e) {
            log.warn("No connection");
            log.warn("try add h2 connection");
            try {
                connection = ConnectionSingleton.getConnection().getH2Connection();
                log.warn("You are in demo mode!");
            } catch (SQLInvalidAuthorizationSpecException e1) {
                log.error("No connection created");
                throw new DatabaseAccessException("data access error");
            }
        }
    }

    private AbstractExecutor(String flush) {
        try {
            log.info(flush);
            connection = ConnectionSingleton.getConnection().getH2Connection();
        } catch (SQLInvalidAuthorizationSpecException e) {
            log.warn("h2 connection wasn't created");
        }
    }

    public static AbstractExecutor getAbstractExecutor(String flush) {
        return new AbstractExecutor(flush);
    }

    public static AbstractExecutor getAbstractExecutor() throws DatabaseAccessException {
        return new AbstractExecutor();
    }

    @Override
    public Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            log.error(" Statement was not created");
        }
        return null;
    }

    /**
     * this method provides all operations to update table in the database
     * @param query is a query is needed to run
     * @throws SQLWarningException if we have warning exception
     * @throws SQLException if we have more serious problem than syntax error or sql syntax error
     * if we have invalid syntax here we throw IllegalArgumentException
     */
    @Override
    public void execUpdate(String query) throws SQLWarningException, SQLException {
        try (Statement statement = createStatement(connection)) {
            statement.execute(query);
            if (statement.getWarnings() != null) {
                log.warn(statement.getWarnings());
                throw new SQLWarningException(statement.getWarnings().getMessage());
            }
        } catch (SQLSyntaxErrorException e) {
            log.error("syntax error here   " + query); // maybe if we do not have this table
            throw new IllegalArgumentException();
        } catch (SQLException e) {
            log.error("sql exception after query " + query);
            throw new SQLException();
        }
    }

    private ResultSet resultSet;
    /**
     * this method provides all functions to get data from the database (1 row and a lot of rows either)
     * @param query is a query needed to run
     * @param result is a aux param which help to handle result set to the entity
     * @throws SQLException if we have database access problem
     * if we have syntax error, we throw SQLSyntaxException
     */
    @Override
    public void execQuery(String query, Handler result) throws SQLException {
        try(Statement statement = createStatement(connection)) {
            log.info(query);
            statement.execute(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                result.handle(resultSet);
            }
            else {
                log.warn("empty result set");
                throw new IllegalArgumentException();
            }
        } catch (SQLSyntaxErrorException e) {
            log.error("sql syntax error");
            throw new SQLSyntaxErrorException();
        } catch (SQLException e) {
            log.error("sql unknown error");
            throw new SQLException();
        } finally {
            if (resultSet != null)
            resultSet.close();
        }
    }
}