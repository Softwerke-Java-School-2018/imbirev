package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Sale;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

abstract class AbstractDbService implements DbInterface<Sale> {

    private ClientDao dao;

    public AbstractDbService() {
        AbstractExecutor executor = null;
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        dao = new ClientDao(executor);
    }


    /**
     * this method add primary key to the table
     * @param tableName
     * @param columnName - which column will be primary
     */
    @Override
    public void modifyTable(String tableName, String columnName) {
        dao.addPrimaryKey(SaleTable.TABLE_NAME,columnName);
    }

    /**
     * this method add foreign key to the table
     * @param tableName
     * @param columnName
     * @param tableRef
     * @param columnRef
     */
    @Override
    public void modifyForeignKey(String tableName, String columnName, String tableRef, String columnRef) {
        dao.addForeignKey(SaleTable.TABLE_NAME,columnName, tableRef, columnRef);
    }

    /**
     * this method drop the table with tableName
     * @param tableName
     */
    @Override
    public void dropTable(String tableName) {
        dao.dropTable(SaleTable.TABLE_NAME);
    }

    /**
     * this method delete item from the table
     * @param tableName
     * @param array - conditions of the deleting
     */
    @Override
    public void deleteFromTable(String tableName ,Query[] array) throws IllegalArgumentException {
        dao.deleteFromTable(tableName,array);
    }

    /**
     * this method update data from the table
     * @param tableName
     * @param id - with this id
     * @param array - with this conditions
     */
    @Override
    public void updateTable(String tableName, String colQuery,  String id, Query[] array) throws IllegalArgumentException {
        dao.updateTable(tableName,colQuery, id, array);
    }
}
