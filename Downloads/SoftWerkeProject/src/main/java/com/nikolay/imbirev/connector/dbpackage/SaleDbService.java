package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.SaleDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.Sale;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.executors.AbstractExecutor;

import java.util.List;

public class SaleDbService extends AbstractDbService {

    private SaleDao dao;

    public SaleDbService() {
        AbstractExecutor executor = new AbstractExecutor();
        dao = new SaleDao(executor);
    }

    /**
     * this method send query to database to add new value
     * @param value - is the value to add
     */
    public void sendToTable(Sale value) {
        dao.createTable(SaleTable.TABLE_NAME, SaleTable.Cols.columns);
        dao.insertIntoTable(new String[] {
                value.getSaleId(),
                value.getClientid(),
                String.valueOf(value.getOverallPrice()),
                value.getDateOfSale().toString()
        }, SaleTable.Cols.columns, SaleTable.TABLE_NAME);
    }

    /**
     * this method get list of objects from the database
     * @param tableName - from where
     * @param array - with what conditions
     * @param sortColumns - sort conditions
     * @return new list or throw IllegalArgumentException
     */
    public List<Sale> getList(String tableName, Query[] array, Column[] sortColumns) {
        try {
            return dao.getItemFromTable(tableName, array, sortColumns);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }
}
