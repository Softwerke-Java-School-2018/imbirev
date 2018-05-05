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
        try {
            service = new DeviceDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
            switch (operation) {
                case "create":
                    return service.insertIntoTable(insertOrUpdateQueries);
                case "update":
                    return service.updateTable(searhQueries, insertOrUpdateQueries);
                case "delete":
                    return service.deleteFromTable(insertOrUpdateQueries);
                case "get":
                    return service.getFromTable(searhQueries, sortColumns);
                default:
                    return RequestCode.SYNTAX_ERROR;
            }
    }
}