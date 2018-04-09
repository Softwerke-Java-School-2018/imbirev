package com.imbirev.nikolay.model.executors;

import com.imbirev.nikolay.model.beans.Client;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/*
this class contains all sql queries
 */
public class ClientDao implements DaoInterface {
    // entity for executor
    private ClientExecutor executor;

    public ClientDao(ClientExecutor executor) {
        this.executor = executor;
    }

    private final static String TABLE_NAME = "person";
    private final static String FIRST_NAME = "first_name";
    private final static String LAST_NAME = "last_name";
    private final static String  PERSON_ID = "person_id";
    private final static String DATE_OF_BIRTH = "date_of_birth";

    /**
     * this method try to complete query to create new table person with 4 fields
     * first_name, last_name, person_id, date_of_birth
     * we use stringBuilder to save the memory, and we also generate the string before to avoid sql injections
     *
     */
    @Override
    public void createTable() {

        StringBuilder queryString = new StringBuilder("create table ")
                .append(TABLE_NAME).append("if not exist ( ")
                .append(FIRST_NAME)
                .append("varchar(256) not null,")
                .append(LAST_NAME)
                .append(" varchar(256) not null,")
                .append(PERSON_ID)
                .append(" bigint not null,")
                .append(DATE_OF_BIRTH)
                .append(" date not null,")
                .append("primary key (")
                .append(PERSON_ID)
                .append("));");
        try {
            executor.createTableMethod(queryString.toString());
        } catch (SQLException e) {
            System.out.println("Table was not created because of SQLException");
            e.printStackTrace();
        }
    }


    /**
     * method get 4 params and insert them into the database (localdate - transform to sqlDate)
     * if smt wrong - throw new SQLException and rollback
     */
    public void insertIntoTable(Client clientBuilder) {

        Date date = Date.valueOf(clientBuilder.getDateOfBirth());
        StringBuilder builder = new StringBuilder("insert into ")
                .append(TABLE_NAME)
                .append(" values (")
                .append(clientBuilder.getfName())
                .append(", ").append(clientBuilder.getlName())
                .append(", ").append(clientBuilder.getClientId())
                .append(", ").append(date)
                .append(");");
        try {
            executor.insertIntoTableMethod(builder.toString());
        } catch (SQLException e) {
            System.out.println("Client was not added because of SQLExeption");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromTable(int id) {
        StringBuilder builder = new StringBuilder("delete from ").append(TABLE_NAME)
                .append("*").append("where ")
                .append(PERSON_ID).append(" =  ").append(id)
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

    /**
     * method get all clients from the database by List
     * @return
     */
    public List<Client> getFromTable() {
        StringBuilder builder = new StringBuilder("select * from ");
        builder.append(TABLE_NAME).append(";");
        try {
            return executor.getFromTableMethod(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Client was not found");
            throw new RuntimeException(e);
        }
    }

}