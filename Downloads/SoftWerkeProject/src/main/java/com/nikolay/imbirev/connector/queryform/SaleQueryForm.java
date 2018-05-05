package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.SaleDbService;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;


@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value
class SaleQueryForm {
    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;

    RequestCode performOperation() {
        SaleDbService service;
        Performer performer = new Performer();
        try {
            service = new SaleDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(SaleTable.Cols.columns);
        if (code == RequestCode.SUCCESS) {
            return performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
        } else return RequestCode.DATABASE_ERROR;
    }
}