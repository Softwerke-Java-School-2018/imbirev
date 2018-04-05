package com.imbirev.nikolay.services;

import java.sql.Connection;

public class DeviceDbService extends DbService {

    private Connection connection;

     DeviceDbService() {
        connection = getMysqlCon();
        printConnectInfo();
    }

}
