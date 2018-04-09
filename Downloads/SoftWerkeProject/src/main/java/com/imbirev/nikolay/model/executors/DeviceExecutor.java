package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.Client;
import com.imbirev.nikolay.model.beans.Device;
import com.imbirev.nikolay.model.beans.DeviceBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Device Executor to work with queries of changing device information
 */
public class DeviceExecutor extends AbstractExecutor implements QueriesInterface {

    private Connection connection;
    private List<Device> deviceList;

    DeviceExecutor(Connection connection) {
        super(connection);
        this.connection = connection;
        deviceList = new ArrayList<>();
    }

    // there some methods, which take only 1 param - query and try to complete it
    @Override
    public int createTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int insertIntoTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int deleteMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    @Override
    public int deleteTableMethod(String query) throws SQLException {
        return execUpdate(query);
    }

    // here is another situation, with ResultSet
    // TODO
    @Override
    public List<Device> getFromTableMethod(String query) throws SQLException {
        ResultSet resultSet = createStatement(connection).getResultSet();
        while (resultSet.next()) {
            DeviceBuilder builder = new DeviceBuilder(0);
            builder.addOrChangeDeviceId(resultSet.getInt(1));
            builder.addOrChangeDeviceModel(resultSet.getString(2));
            builder.addOrChangeDeviceColor(resultSet.getString(3));
            builder.addOrChangeDeviceManufactorer(resultSet.getString(4));
            builder.addOrChangeDeviceType(resultSet.getString(5));
            builder.addOrChangeDevicePrice(resultSet.getDouble(6));

            deviceList.add(new Device(builder));
        }
        return deviceList;
    }
}
