package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.connector.dbpackage.DeviceDbService;
import com.nikolay.imbirev.connector.savers.DeviceSaver;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.view.ListViewer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import java.util.Collections;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
class DeviceQueryForm {

    private String operation;
    private Column[] sortColumns;
    private Query[] searhQueries;
    private Query[] insertOrUpdateQueries;

    static DeviceQueryForm getClientQueryForm(String operation, Column[] sortColumns, Query[] searhQueries, Query[] insertOrUpdateQueries) {
        return new DeviceQueryForm(operation, sortColumns, searhQueries, insertOrUpdateQueries);
    }

    RequestCode performOperation() {
        DeviceDbService service;
        Performer performer = Performer.getPerformer();
        try {
            service = DeviceDbService.getDeviceDbService();
        } catch (DatabaseAccessException e) {
            return RequestCode.DATABASE_ERROR;
        }
        RequestCode code = service.createTable(DeviceTable.Cols.getCOLUMNS());
        if (code == RequestCode.SUCCESS) {
            RequestCode requestCode =
                    performer.perform(service, operation, sortColumns, searhQueries, insertOrUpdateQueries);
            if (operation.equals("get") && requestCode == RequestCode.SUCCESS) {
                new ListViewer<Device>().listView(Collections.unmodifiableList(DeviceSaver.getInstance().getDeviceList()));
            }
            return requestCode;
        } else return RequestCode.DATABASE_ERROR;
    }
}