package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.SaleDbService;
import com.nikolay.imbirev.connector.savers.SaleSaver;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.view.ListViewer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import java.util.Collections;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
class SaleQueryForm {
    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;

    static SaleQueryForm getClientQueryForm(String operation, Column[] sortColumns, Query[] searhQueries, Query[] insertOrUpdateQueries) {
        return new SaleQueryForm(operation, sortColumns, searhQueries, insertOrUpdateQueries);
    }

    RequestCode performOperation() {
        SaleDbService service;
        Performer performer = Performer.getPerformer();
        try {
            service = SaleDbService.getSaleDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(SaleTable.Cols.getListOfColumns());
        if (code == RequestCode.SUCCESS || code == RequestCode.WARNING) {
            RequestCode requestCode =
                    performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
            if (operation.equals("get") && requestCode == RequestCode.SUCCESS) {
                new ListViewer<Sale>().listView(Collections.unmodifiableList(SaleSaver.getInstance().getSaleList()));
            }
            return requestCode;
        } else return RequestCode.DATABASE_ERROR;
    }
}