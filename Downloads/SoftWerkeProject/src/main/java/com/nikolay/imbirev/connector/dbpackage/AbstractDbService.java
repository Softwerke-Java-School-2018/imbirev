package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.entities.DeviceTable;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.Sale;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

abstract class AbstractDbService implements DbInterface<Sale> {

    private AbstractDao dao;

    public AbstractDbService() {
        AbstractExecutor executor = new AbstractExecutor();
        dao = new AbstractDao(executor);
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
    public void deleteFromTable(String tableName ,Query[] array) {
        dao.deleteFromTable(SaleTable.TABLE_NAME,array);
    }

    /**
     * this method update data from the table
     * @param tableName
     * @param id - with this id
     * @param array - with this conditions
     */
    @Override
    public void updateTable(String tableName, String id, Query[] array) {
        dao.updateTable(tableName, id, array);
    }
}
