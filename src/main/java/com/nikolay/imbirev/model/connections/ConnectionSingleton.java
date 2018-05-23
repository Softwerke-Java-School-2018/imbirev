package com.nikolay.imbirev.model.connections;

import lombok.Synchronized;
import lombok.extern.log4j.Log4j;
import org.h2.jdbcx.JdbcDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@Log4j
public class ConnectionSingleton {

    private static ConnectionSingleton sConnectionSingleton;

    private ConnectionSingleton() {

    }

    @Synchronized
    public static ConnectionSingleton getConnection() {
        if (sConnectionSingleton == null) {
            log.info("new connection");
            sConnectionSingleton = new ConnectionSingleton();
        }
        return sConnectionSingleton;
    }

    public Connection getMySQlConnection() throws SQLInvalidAuthorizationSpecException {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            properties.load(inputStream);
            String url = "jdbc:mysql://" +        //db type
                    "localhost:" +           //host name
                    "3306/" +                //port
                    "Krohne?" +          //db name
                    "user=root&" +          //login
                    "password=" +
                    properties.getProperty("db.password") +//password
                    "&useSSL=false&serverTimezone=Europe/Moscow"; // timezone
            log.info(url);
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new SQLInvalidAuthorizationSpecException();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public Connection getH2Connection() throws SQLInvalidAuthorizationSpecException {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = "jdbc:h2:./testDb";
            String name = properties.getProperty("h2.name");
            String pass = properties.getProperty("h2.password");

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            throw new SQLInvalidAuthorizationSpecException();
        }catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
