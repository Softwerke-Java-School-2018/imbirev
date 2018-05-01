package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.savers.DeviceSaver;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.sun.istack.internal.NotNull;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDao extends AbstractDao {

    private AbstractExecutor abstractExecutor;
    private List<Device> devices;

    public DeviceDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        devices = new ArrayList<>();
    }

    /**
     * this method try to find some data from the database
     * @param tableName is the table where we will try find data (can not be null)
     * @param array is query conditions for search information
     * @param sortColumns is an array of sorting columns to determine the order of the results
     * @return success code and add list of devices to the singleton or return unsuccessful code
     */
    public RequestCode getListFromTable(@NotNull String tableName, Query[] array, Column[] sortColumns) {
        if (tableName == null || tableName.equals("")) return RequestCode.SYNTAX_ERROR;
        StringBuilder query = new StringBuilder();
        query.append(execQueryOperation(tableName,array, sortColumns));
        try {
            abstractExecutor.execQuery(query.toString(), resultSet -> {
                while (resultSet.next()) {
                    Device device = Device.builder()
                            .deviceId(resultSet.getString(DeviceTable.Cols.ID))
                            .dateOfManufactoringStarted(resultSet.getDate(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING).toLocalDate())
                            .model(resultSet.getString(DeviceTable.Cols.MODEL))
                            .price(resultSet.getDouble(DeviceTable.Cols.PRICE))
                            .producer(resultSet.getString(DeviceTable.Cols.PRODUCER))
                            .color(resultSet.getString(DeviceTable.Cols.COLOR))
                            .type(resultSet.getString(DeviceTable.Cols.TYPE))
                            .build();
                    devices.add(device);
                }
            });
            DeviceSaver.getInstance().setDeviceList(devices);
            return RequestCode.SUCCESS;
        } catch(IllegalArgumentException e) {
            return RequestCode.EMPTY_SET;
        } catch(SQLSyntaxErrorException e){
            return RequestCode.SQL_SYNTAX_ERROR;
        } catch(SQLException e){
            return RequestCode.DATABASE_ERROR;
        }
    }
}
