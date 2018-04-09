package com.imbirev.nikolay.model.executors;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * interface for adding conditions to work with dao classes
 */
public interface QueriesInterface<T> {

    /**
     * method for createing tables
     * @param query
     * @return
     */
    public abstract int createTableMethod(String query) throws SQLException;

    /**
     * insert into table
     * @param query
     * @return
     */
    public abstract int insertIntoTableMethod(String query) throws SQLException;

    /**
     * delete from table
     * @param query
     * @return
     */
    public abstract int deleteMethod(String query) throws SQLException;

    /**
     * drop tables
     * @param query
     * @return
     */
    public abstract int deleteTableMethod(String query) throws SQLException;

    /**
     * get data
     * @param query
     * @return
     */
    public abstract List<T> getFromTableMethod(String query) throws SQLException;
}
