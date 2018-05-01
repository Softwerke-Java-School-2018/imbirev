package com.nikolay.imbirev.connector.checker;

import com.nikolay.imbirev.connector.dbpackage.DeviceDbService;
import com.nikolay.imbirev.model.entities.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DeviceChecker implements CheckerInterface<Device> {

    private DeviceDbService service;
    private Query[] queries;

    public DeviceChecker() {
        //service = new DeviceDbService();
    }

    private Query[] getQueryArray(String[] columns, String[] newData) {
        Query[] queries = new Query[columns.length];
        if (queries.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].trim().equals("model")) {
                queries[i] = new Query(DeviceTable.Cols.MODEL, newData[i]);
            }
            if (columns[i].trim().equals("producer")) {
                queries[i] = new Query(DeviceTable.Cols.PRODUCER, newData[i]);
            }
            if (columns[i].trim().equals("date of start")) {
                queries[i] = new Query(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING, Date.valueOf(LocalDate.parse(newData[i], DateTimeFormatter.ofPattern("d/MM/yyyy"))).toString());
            }
            if (columns[i].trim().equals("price")) {
                queries[i] = new Query(DeviceTable.Cols.PRICE, newData[i]);
            }
            if (columns[i].trim().equals("type")) {
                queries[i] = new Query(DeviceTable.Cols.TYPE, newData[i]);
            }
            if (columns[i].trim().equals("color")) {
                queries[i] = new Query(DeviceTable.Cols.COLOR, newData[i]);
            }
        }
        return queries;
    }
//    private Column[] getSortColumns(String[] cols) {
//        if (cols.length == 0) return new Column[] {};
//        Column[] columns = new Column[cols.length];
//        for (int i = 0; i < columns.length; i++) {
//            if (cols[i].trim().equals("model")) {
//                columns[i] = new Column(DeviceTable.Cols.MODEL, null, false, false);
//            }
//            if (cols[i].trim().equals("producer")) {
//                columns[i] = new Column(DeviceTable.Cols.PRODUCER, null, false, false);
//            }
//            if (cols[i].trim().equals("date of start")) {
//                columns[i] = new Column(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING, null, false, false);
//            }
//            if (cols[i].trim().equals("type")) {
//                columns[i] = new Column(DeviceTable.Cols.TYPE, null, false, false);
//            }
//            if (cols[i].trim().equals("price")) {
//                columns[i] = new Column(DeviceTable.Cols.PRICE, null, false, false);
//            }
//            if (cols[i].trim().equals("color")) {
//                columns[i] = new Column(DeviceTable.Cols.COLOR, null, false, false);
//            }
//        }
//        return columns;
 //   }

    @Override
    public void addToTable(Device object) throws IllegalArgumentException {
       // service.sendToTable(object);
    }

    public void updateTable(Device object, String[] cols, String[] vals) {
        Device device = getFromTable(new String[]{"model"}, new String[]{object.getModel()});
        queries = getQueryArray(cols, vals);
        if (device != null) {
           // service.updateTable(DeviceTable.TABLE_NAME, DeviceTable.Cols.ID, device.getDeviceId(), queries);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteFromTable(Device object) throws IllegalArgumentException {
//        service.deleteFromTable(DeviceTable.TABLE_NAME, new Query[]{
//                new Query(DeviceTable.Cols.MODEL, object.getModel()),
//                new Query(DeviceTable.Cols.PRICE, String.valueOf(object.getPrice())),
//                new Query(DeviceTable.Cols.PRODUCER, object.getProducer()),
//                new Query(DeviceTable.Cols.TYPE, object.getType()),
//                new Query(DeviceTable.Cols.COLOR, object.getColor()),
//                new Query(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING, object.getDateOfManufactoringStarted().toString())
//        });
    }

    @Override
    public Device getFromTable(String[] cols, String[] vals) {
        return null;
    }

//    @Override
//    public Device getFromTable(String[] cols, String[] vals) {
//        queries = getQueryArray(cols, vals);
//        return service.getDevice(DeviceTable.TABLE_NAME, queries);
//    }

//    public List<Device> getListOfDevices(String[] cols, String[] vals, String[] sort) {
//        List<Device> devices;
//        if (cols.length > 0) {
//            queries = getQueryArray(cols, vals);
//            devices = service.getList(DeviceTable.TABLE_NAME, queries, getSortColumns(sort));
//            return devices;
//        } else {
//            devices = service.getList(DeviceTable.TABLE_NAME, new Query[]{}, getSortColumns(sort));
//            return devices;
//        }
//    }

    @Override
    public void deleteTable(String tableName) {
        //service.dropTable(tableName);
    }

    @Override
    public List<Command> help() {
        return CommandHolder.getCommandHolder().getCommandList();
    }
}
