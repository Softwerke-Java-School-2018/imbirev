package com.imbirev.nikolay.executors;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * interface for adding conditions to work with dao classes
 */
public interface QueriesInterface {

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
     * update table
     * @param query
     * @return
     */
    public abstract int updateTableMethod(String query) throws SQLException;

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
    public abstract ResultSet getFromTableMethod(String query) throws SQLException;
}
