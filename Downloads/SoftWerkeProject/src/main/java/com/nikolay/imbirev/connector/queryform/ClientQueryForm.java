package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.connector.savers.SaverClients;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.view.ListViewer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value
class ClientQueryForm {

    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;


    RequestCode performOperation() {
        ClientDbService service;
        Performer performer = new Performer();
        try {
            service = new ClientDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(ClientTable.Cols.columns);
        if (code == RequestCode.SUCCESS) {
            RequestCode requestCode =
                    performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
            if (operation.equals("get") && requestCode == RequestCode.SUCCESS) {
                new ListViewer<Client>().listView(SaverClients.getInstance().getClients());
            }
            return requestCode;
        } else return RequestCode.DATABASE_ERROR;
    }
}