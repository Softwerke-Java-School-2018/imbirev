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

    public void sendToTable(Sale value) {
        dao.createTable(SaleTable.TABLE_NAME, SaleTable.Cols.columns);
        dao.insertIntoTable(new String[] {
                value.getSaleId(),
                value.getClientid(),
                String.valueOf(value.getOverallPrice()),
                value.getDateOfSale().toString()
        }, SaleTable.Cols.columns, SaleTable.TABLE_NAME);
    }

    public List<Sale> getList(String tableName, Query[] array, Column[] sortColumns) {
        try {
            return dao.getItemFromTable(tableName, array, sortColumns);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }
}
