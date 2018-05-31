package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.DbInterface;
import com.nikolay.imbirev.model.entities.*;
import lombok.extern.log4j.Log4j;
@Log4j
class Performer {

    static Performer getPerformer() {
        return new Performer();
    }

    RequestCode perform(DbInterface service, String operation, Column[] sortColumns, Query[] searchQueries, Query[] insertOrUpdateQueries) {
        switch (operation) {
            case "create":
                return service.insertIntoTable(insertOrUpdateQueries);
            case "update":
                RequestCode code = service.getFromTable(searchQueries, new Column[]{});
                if (code == RequestCode.SUCCESS) {
                    return service.updateTable(searchQueries, insertOrUpdateQueries);
                } else return RequestCode.EMPTY_SET;
            case "delete":
                RequestCode code1 = service.getFromTable(searchQueries, new Column[]{});
                if (code1 == RequestCode.SUCCESS) {
                    return service.deleteFromTable(insertOrUpdateQueries);
                } else return RequestCode.EMPTY_SET;
            case "get":
                return service.getFromTable(searchQueries, sortColumns);
            default:
                log.error(RequestCode.SYNTAX_ERROR);
                return RequestCode.SYNTAX_ERROR;
        }
    }
}