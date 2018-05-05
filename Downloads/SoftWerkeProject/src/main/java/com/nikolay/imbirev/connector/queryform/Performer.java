package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.DbInterface;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;

class Performer {

    RequestCode perform(DbInterface service, String operation, Column[] sortColumns, Query[] searhQueries, Query[] insertOrUpdateQueries) {
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
