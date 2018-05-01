package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.connector.dbpackage.SaleDbService;
import com.nikolay.imbirev.model.entities.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaleChecker implements CheckerInterface<Sale> {

    private SaleDbService service;
    private Query[] queries;

    public SaleChecker() {
        service = new SaleDbService();
    }


    @Override
    public void addToTable(Sale object) throws IllegalArgumentException {
        service.sendToTable(object);
    }

    @Override
    public void deleteFromTable(Sale object) {

    }

    @Override
    public Sale getFromTable(String[] cols, String[] vals) {
        return null;
    }

//    @Override
//    public void deleteFromTable(Sale object) throws IllegalArgumentException {
//        service.deleteFromTable(SaleTable.TABLE_NAME, new Query[]{
//                new Query(SaleTable.Cols.ClIENT_ID, object.getClientid()),
//                new Query(SaleTable.Cols.DATE_OF_SALE, object.getDateOfSale().toString()),
//                new Query(SaleTable.Cols.PRICE, String.valueOf(object.getOverallPrice()))
//        });
//    }
//
//    @Override
//    public Sale getFromTable(String[] cols, String[] vals) {
//        queries = getQueryArray(cols, vals);
//        return service.getSale(SaleTable.TABLE_NAME, queries);
//    }

    private Query[] getQueryArray(String[] columns, String[] newData) {
        Query[] queries = new Query[columns.length];
        if (queries.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].trim().equals("price")) {
                queries[i] = new Query(SaleTable.Cols.PRICE, newData[i]);
            }
            if (columns[i].trim().equals("clientId")) {
                queries[i] = new Query(SaleTable.Cols.ClIENT_ID, newData[i]);
            }
            if (columns[i].trim().equals("date of sale")) {
                queries[i] = new Query(SaleTable.Cols.DATE_OF_SALE, Date.valueOf(LocalDate.parse(newData[i], DateTimeFormatter.ofPattern("d/MM/yyyy"))).toString());
            }
        }
        return queries;
    }

    @Override
    public void deleteTable(String tableName) {
        service.dropTable(tableName);
    }

    @Override
    public List<Command> help() {
        return CommandHolder.getCommandHolder().getCommandList();
    }

//    public List<Sale> getList(String[] cols, String[] data, String[] sortCols) {
//        if (cols.length > 0) {
//            return service.getList(SaleTable.TABLE_NAME, getQueryArray(cols, data), getSortColumns(sortCols));
//        } else {
//            return service.getList(SaleTable.TABLE_NAME, new Query[]{}, getSortColumns(sortCols));
//        }
//    }

//    private Column[] getSortColumns(String[] cols) {
//        if (cols.length == 0) return new Column[] {};
//        Column[] columns = new Column[cols.length];
//        for (int i = 0; i < columns.length; i++) {
//            if (cols[i].trim().equals("price")) {
//                columns[i] = new Column(SaleTable.Cols.PRICE, null, false, false);
//            }
//            if (cols[i].trim().equals("clientId")) {
//                columns[i] = new Column(SaleTable.Cols.ClIENT_ID, null, false, false);
//            }
//            if (cols[i].trim().equals("date of sale")) {
//                columns[i] = new Column(SaleTable.Cols.DATE_OF_SALE, null, false, false);
//            }
//        }
//        return columns;
//    }

//    public void updateSale(Sale sale, String[] columns, String[] newData) throws IllegalArgumentException {
//        Sale s = getFromTable(new String[]{"clientId", "price"},
//                new String[]{sale.getClientid(), String.valueOf(sale.getOverallPrice())});
//        queries = getQueryArray(columns, newData);
//        if (s != null) {
//            service.updateTable(SaleTable.TABLE_NAME, SaleTable.Cols.ID, s.getSaleId(), queries);
//        }
//        else {
//            throw new IllegalArgumentException();
//        }
//    }
}
