package com.nikolay.imbirev.model.dao;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.model.entities.*;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import com.nikolay.imbirev.model.executors.Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDao extends AbstractDao {

    AbstractExecutor abstractExecutor;
    private List<Device> devices;


    public DeviceDao(AbstractExecutor executor) {
        super(executor);
        abstractExecutor = executor;
        devices = new ArrayList<>();
    }

    /**
     * this method return item from the database
     * @param tableName - from this table
     * @param array - with this conditions
     * @return new client or throw nullPointerException if we have no client for this conditions
     */
    public List<Device> getListFromTable(String tableName, Query[] array, Column[] sortColumns) {
        StringBuilder query = new StringBuilder()
                .append("select * from ").append(tableName).append(" where ");
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < array.length; i++) {
            if (i == array.length-1) {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
            }
            else {
                query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("', ");
            }
        }
        if (sortColumns.length == 0) {
            query.append(");");
        }
        else {
            query.append(" order by ");
            for (int i = 0; i < sortColumns.length; i++) {
                if (i == sortColumns.length-1) {
                    query.append(sortColumns[i]);
                }
                else {
                    query.append(sortColumns[i]).append(", ");
                }
            }
            query.append(");");
        }
        abstractExecutor.execQuery(query.toString(), new Handler<List<Device>>() {
            @Override
            public List<Device> handle(ResultSet resultSet) throws SQLException {
                resultSet.next();
                String id = resultSet.getString(1);
                if(id.substring(0, 2).equals("dev")) {
                    while (resultSet.next()) {
                        Device device = new Device.DeviceBuilder()
                                .setDeviceId(id)
                                .setDate(resultSet.getDate(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING).toLocalDate())
                                .setModel(resultSet.getString(DeviceTable.Cols.MODEL))
                                .setPrice(resultSet.getDouble(DeviceTable.Cols.PRICE))
                                .setProducer(resultSet.getString(DeviceTable.Cols.PRODUCER))
                                .setColor(resultSet.getString(DeviceTable.Cols.COLOR))
                                .setType(resultSet.getString(DeviceTable.Cols.TYPE))
                                .build();
                        devices.add(device);
                    }
                }
                return devices;
            }
        });
        throw new NullPointerException();
    }
}
