package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.model.dao.SaleDao;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.connector.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class SaleDbService implements DbInterface {

    private SaleDao dao;
    private static final String TAG = "SaleDbService";

    private SaleDbService() throws DatabaseAccessException {
        AbstractExecutor executor;
        try {
            executor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(TAG);
            throw new DatabaseAccessException(e.getMessage());
        }
        dao = new SaleDao(executor);
    }

    public static SaleDbService getSaleDbService() throws DatabaseAccessException {
        return new SaleDbService();
    }

    @Override
    public RequestCode createTable(List<Column> array) {
        return dao.createTable(SaleTable.TABLE_NAME, array.toArray(new Column[0]));
    }

    @Override
    public RequestCode deleteFromTable(Query[] array) {
        return dao.deleteFromTable(SaleTable.TABLE_NAME, array);
    }

    @Override
    public RequestCode updateTable(Query[] condArray, Query[] newArray) {
        return dao.updateTable(SaleTable.TABLE_NAME, condArray, newArray);
    }

    @Override
    public RequestCode insertIntoTable(Query[] array) {
        return dao.insertIntoTable(array, SaleTable.TABLE_NAME);
    }

    @Override
    public RequestCode getFromTable(Query[] array, Column[] sortArray) {
        return dao.getListFromTable(SaleTable.TABLE_NAME, array, sortArray);
    }
}
