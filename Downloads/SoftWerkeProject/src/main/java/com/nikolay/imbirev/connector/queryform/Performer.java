package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.DbInterface;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;

class Performer {

    static Performer getPerformer() {
        return new Performer();
    }

    RequestCode perform(DbInterface service, String operation, Column[] sortColumns, Query[] searhQueries, Query[] insertOrUpdateQueries) {
        switch (operation) {
            case "create":
                return service.insertIntoTable(insertOrUpdateQueries);
            case "update":
                RequestCode code = service.getFromTable(searhQueries, new Column[]{});
                if (code == RequestCode.SUCCESS) {
                    return service.updateTable(searhQueries, insertOrUpdateQueries);
                } else return RequestCode.EMPTY_SET;
            case "delete":
                return service.deleteFromTable(insertOrUpdateQueries);
            case "get":
                return service.getFromTable(searhQueries, sortColumns);
            default:
                return RequestCode.SYNTAX_ERROR;
        }
    }
}
