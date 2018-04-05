package com.imbirev.nikolay.executors;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * интерфейс для описания методов по различным изменениям бд
 */
public interface QueriesInterface {

    /**
     * метод для создания таблиц
     * @param query
     * @return
     */
    public abstract int createTableMethod(String query) throws SQLException;

    /**
     * добавление в таблицу
     * @param query
     * @return
     */
    public abstract int insertIntoTableMethod(String query) throws SQLException;

    /**
     * изменение в таблице
     * @param query
     * @return
     */
    public abstract int updateTableMethod(String query) throws SQLException;

    /**
     * удаление конкретных записей
     * @param query
     * @return
     */
    public abstract int deleteMethod(String query) throws SQLException;

    /**
     * удаление таблиц
     * @param query
     * @return
     */
    public abstract int deleteTableMethod(String query) throws SQLException;

    /**
     * получаем данные из таблицы
     * @param query
     * @return
     */
    public abstract ResultSet getFromTableMethod(String query) throws SQLException;
}
