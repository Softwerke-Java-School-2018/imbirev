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


    @Override
    public void modifyTable(String tableName, String columnName) {
        dao.addPrimaryKey(SaleTable.TABLE_NAME,columnName);
    }

    @Override
    public void modifyForeignKey(String tableName, String columnName, String tableRef, String columnRef) {
        dao.addForeignKey(SaleTable.TABLE_NAME,columnName, tableRef, columnRef);
    }

    @Override
    public void dropTable(String tableName) {
        dao.dropTable(SaleTable.TABLE_NAME);
    }

    @Override
    public void deleteFromTable(String tableName ,Query[] array) {
        dao.deleteFromTable(SaleTable.TABLE_NAME,array);
    }

    @Override
    public void updateTable(String tableName, String id, Query[] array) {
        dao.updateTable(tableName, id, array);
    }
}
