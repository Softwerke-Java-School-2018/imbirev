package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.IndependentSale;

import java.sql.SQLException;
import java.util.List;

/*
standart class with all query methods to database
 */
public class SaleDao implements DaoInterface {

    private SaleExecutor executor;

    public SaleDao(SaleExecutor executor) {
        this.executor = executor;
    }

    // necessary constants which are column names in table
    private final static String TABLE_NAME = "sale";
    private final static String SALE_ID = "sale_id";
    private final static String DEVICE_ID = "device_id";
    private final static String CLIENT_ID = "client_id";
    private final static String NUMBER_OF_DEVICES = "number_of_devices";
    private final static String SALE_DATE = "sale_date";

    @Override
    public void createTable() {
        StringBuilder builder = new StringBuilder("create table ")
                .append(TABLE_NAME).append("if not exist (")
                .append(SALE_ID).append(" ").append("bigint not null, ")
                .append(DEVICE_ID).append(" ").append("bigint not null, ")
                .append(CLIENT_ID).append(" ").append("bigint not null, ")
                .append(NUMBER_OF_DEVICES).append(" ").append("smallint not null, ")
                .append(SALE_DATE).append(" ").append("date not null, ")
                .append("primary key (").append(SALE_ID).append("));");
        try {
            executor.createTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table was not created");
        }
    }

    public void insertIntoTable(IndependentSale sale) {
        StringBuilder builder = new StringBuilder("insert into ")
                .append(TABLE_NAME)
                .append(" values (")
                .append(sale.getSaleId())
                .append(", ").append(sale.getDeviceId())
                .append(", ").append(sale.getClientId())
                .append(", ").append(sale.getNumberOfDevices())
                .append(", ").append(sale.getSaleDate())
                .append(");");
        try {
            executor.insertIntoTableMethod(builder.toString());
        } catch (SQLException e) {
            System.out.println("Device was not added because of SQLExeption");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromTable(int id) {
        StringBuilder builder = new StringBuilder("delete from ").append(TABLE_NAME)
                .append("*").append("where ")
                .append(SALE_ID).append(" =  ").append(id)
                .append(");");
        try {
            executor.deleteMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("String was not be deleted");
        }
    }

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

    public List<IndependentSale> getFromTableMethod() {
        StringBuilder builder = new StringBuilder("select * from ");
        builder.append(TABLE_NAME).append(";");
        try {
            return executor.getFromTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Sale was not found");
            throw new RuntimeException(e);
        }
    }
}