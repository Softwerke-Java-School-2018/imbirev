package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

abstract class AbstractExecutor {

    private Connection connection;

    /**
     * конструктор экзекьютора
     * @param connection
     */
    AbstractExecutor(Connection connection) {
        this.connection = connection;
    }

    private Statement createStatement(Connection connection) throws SQLException {
        return createStatement(connection);
    }

    /**
     *
     * @param sqlQuery
     * выполняем запрос
     * если все хорошо - комитимся, закрываем statement, выводим 1
     * если ловим исключение - вылетаем - выбрасываем проблему, откатываемся
     * @return
     * @throws SQLException
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
