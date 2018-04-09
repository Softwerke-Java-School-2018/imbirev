package com.imbirev.nikolay.model.singletons;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceSingleton {
    /**
     * here we see singleton to connection to db
     */
    private static ServiceSingleton sServiceSingleton;

    private Connection connection;


    private ServiceSingleton() {
        connection = getMysqlCon();
        printConnectInfo();
    }

    public static ServiceSingleton getsServiceSingleton() {
        if (sServiceSingleton == null) {
            sServiceSingleton = new ServiceSingleton();
        }
        return sServiceSingleton;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * all necessary urls are here
     * @return if everything is okay, we create new connection
     * else throw exception and go away
     */
    private Connection getMysqlCon() {

        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("SoftWerke?").          //db name
                    append("user=root&").          //login
                    append("password=12345678").
                    append("&useSSL=false");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method for printing connection info
     */
    private void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
