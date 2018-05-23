package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.connector.savers.SaverClients;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.view.ListViewer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import java.util.Collections;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class ClientQueryForm {

    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;

    static ClientQueryForm getClientQueryForm(String operation, Column[] sortColumns, Query[] searhQueries, Query[] insertOrUpdateQueries) {
        return new ClientQueryForm(operation, sortColumns, searhQueries, insertOrUpdateQueries);
    }

    public RequestCode performOperation() {
        ClientDbService service;
        Performer performer = Performer.getPerformer();
        try {
            service = ClientDbService.getClientDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(ClientTable.Cols.getCOLUMNS());
        if (code == RequestCode.SUCCESS || code == RequestCode.WARNING) {
            RequestCode requestCode =
                    performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
            if (operation.equals("get") && requestCode == RequestCode.SUCCESS) {
                new ListViewer<Client>().listView(Collections.unmodifiableList(SaverClients.getInstance().getClients()));
            }
            return requestCode;
        } else return RequestCode.DATABASE_ERROR;
    }
}