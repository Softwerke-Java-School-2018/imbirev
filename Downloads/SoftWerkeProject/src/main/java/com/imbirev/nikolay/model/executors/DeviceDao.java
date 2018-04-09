package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.Device;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
this class contains all necessary quesries to db
 */
public class DeviceDao implements DaoInterface {

    // executor with device db methods
    private DeviceExecutor executor;

    public DeviceDao(DeviceExecutor executor) {
        this.executor = executor;
    }

    // constants for databse connection

    private final static String TABLE_NAME = "device";
    private final static String DEVICE_ID = "device_id";
    private final static String MODEL = "model";
    private final static String COLOR = "color";
    private final static String MANUFACTURER = "manufacturer";
    private final static String TYPE = "type";
    private final static String PRICE = "price";

    /**
     * this method creates new table
     */
    @Override
    public void createTable() {
        StringBuilder builder = new StringBuilder("create table ")
                .append(TABLE_NAME).append("if not exist (")
                .append(DEVICE_ID).append(" ").append("bigint not null, ")
                .append(MODEL).append(" ").append("varchar(256) not null, ")
                .append(COLOR).append(" ").append("enum('white', 'black', 'red', 'blue') not null, ")
                .append(MANUFACTURER).append(" ").append("varchar(256) not null, ")
                .append(TYPE).append(" ").append("enum('phone', 'tablet', 'notebook', 'player') not null, ")
                .append(PRICE).append(" ").append("decimal not null, ")
                .append("primary key (").append(DEVICE_ID).append("));");
        try {
            executor.createTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table was not created");
        }
    }

    // this method insert new object to database
    public void insertIntoTable(Device deviceBuilder) {
        StringBuilder builder = new StringBuilder("insert into ")
                .append(TABLE_NAME)
                .append(" values (")
                .append(deviceBuilder.getDeviceId())
                .append(", ").append(deviceBuilder.getModel())
                .append(", ").append(deviceBuilder.getColor())
                .append(", ").append(deviceBuilder.getType())
                .append(", ").append(deviceBuilder.getPrice())
                .append(");");
        try {
            executor.insertIntoTableMethod(builder.toString());
        } catch (SQLException e) {
            System.out.println("Device was not added because of SQLExeption");
            e.printStackTrace();
        }
    }

    // this method delete data from database
    @Override
    public void deleteFromTable(int id) {
        StringBuilder builder = new StringBuilder("delete from ").append(TABLE_NAME)
                .append("*").append("where ")
                .append(DEVICE_ID).append(" =  ").append(id)
                .append(");");
        try {
            executor.deleteMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("String was not be deleted");
        }
    }

    // this method drop table from database
    @Override
    public void dropTable() {
        StringBuilder builder = new StringBuilder("drop table ").append(TABLE_NAME).append(";");
        try {
            executor.deleteTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table wasnt be dropped");
        }
    }

    // this method return list of devices
    public List<Device> getFromTable() {
        StringBuilder builder = new StringBuilder("select * from ");
        builder.append(TABLE_NAME).append(";");
        try {
            return executor.getFromTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Device was not found");
            throw new RuntimeException(e);
        }
    }
}