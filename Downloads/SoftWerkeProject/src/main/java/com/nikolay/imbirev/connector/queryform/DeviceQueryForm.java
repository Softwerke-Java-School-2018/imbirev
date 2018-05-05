package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.DeviceDbService;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.DeviceTable;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value
class DeviceQueryForm {

    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;

    RequestCode performOperation() {
        DeviceDbService service;
        Performer performer = new Performer();
        try {
            service = new DeviceDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(DeviceTable.Cols.columns);
        if (code == RequestCode.SUCCESS) {
            return performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
        } else return RequestCode.DATABASE_ERROR;
    }
}