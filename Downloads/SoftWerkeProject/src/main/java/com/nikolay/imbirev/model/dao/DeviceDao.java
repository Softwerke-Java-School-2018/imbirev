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
    private Device mDevice;


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
                .append("select * from ").append(tableName);
        if (array.length != 0) {
            query.append(" where ");
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
                } else {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("', ");
                }
            }
        }
        if (sortColumns.length == 0) {
            query.append(";");
        }
        else {
            query.append(" order by ");
            for (int i = 0; i < sortColumns.length; i++) {
                if (i == sortColumns.length-1) {
                    query.append(sortColumns[i].getColumnName());
                }
                else {
                    query.append(sortColumns[i].getColumnName()).append(", ");
                }
            }
            query.append(";");
        }
        abstractExecutor.execQuery(query.toString(), new Handler<List<Device>>() {
            @Override
            public List<Device> handle(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    Device device = new Device.DeviceBuilder()
                            .setDeviceId(resultSet.getString(DeviceTable.Cols.ID))
                            .setDate(resultSet.getDate(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING).toLocalDate())
                            .setModel(resultSet.getString(DeviceTable.Cols.MODEL))
                            .setPrice(resultSet.getDouble(DeviceTable.Cols.PRICE))
                            .setProducer(resultSet.getString(DeviceTable.Cols.PRODUCER))
                            .setColor(resultSet.getString(DeviceTable.Cols.COLOR))
                            .setType(resultSet.getString(DeviceTable.Cols.TYPE))
                            .build();
                    devices.add(device);
                }
                return devices;
            }
        });
        return devices;
    }
    /**
     * this method return item from the database
     * @param tableName - from this table
     * @param array - with this conditions
     * @return new client or throw nullPointerException if we have no client for this conditions
     */
    public Device getItemFromTable(String tableName, Query[] array) {
        StringBuilder query = new StringBuilder()
                .append("select * from ").append(tableName).append(" where ");
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("'");
                } else {
                    query.append(array[i].getColumnName()).append(" = '").append(array[i].getColumnQuery()).append("' and ");
                }
            }
            System.out.println(query.toString());
            abstractExecutor.execQuery(query.toString(), new Handler<Device>() {
                @Override
                public Device handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()) {
                        Device device = new Device.DeviceBuilder()
                                .setDeviceId(resultSet.getString(DeviceTable.Cols.ID))
                                .setModel(resultSet.getString(DeviceTable.Cols.MODEL))
                                .setColor(resultSet.getString(DeviceTable.Cols.COLOR))
                                .setPrice(resultSet.getDouble(DeviceTable.Cols.PRICE))
                                .setProducer(resultSet.getString(DeviceTable.Cols.PRODUCER))
                                .setType(resultSet.getString(DeviceTable.Cols.TYPE))
                                .setDate(resultSet.getDate(DeviceTable.Cols.DATE_OF_STARTING_MANUFACTORING).toLocalDate())
                                .build();
                        mDevice = device;
                        return mDevice;
                    } else {
                        return null;
                    }
                }
            });
            return mDevice;
        }
    }
}
