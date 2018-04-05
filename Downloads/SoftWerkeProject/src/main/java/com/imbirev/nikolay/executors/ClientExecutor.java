package com.imbirev.nikolay.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Экзекьютор для работы с методами по изменению бд в области клиента
 */
public class ClientExecutor extends AbstractExecutor implements QueriesInterface {

    /**
     * конструктор экзекьютора
     *
     * @param connection
     */
    protected ClientExecutor(Connection connection) {
        super(connection);
    }

    // перечень методов которые по сути делают update
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

    // тут уже по интереснее
    // TODO обработать метод, добавить к нему обработчик в виде интерфейса
    @Override
    public ResultSet getFromTableMethod(String query) throws SQLException {
        return null;
    }
}
