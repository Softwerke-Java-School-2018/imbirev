package com.imbirev.nikolay.services;

import java.sql.Connection;

public class ClientDbService extends DbService {

    private Connection connection;

     ClientDbService() {
        connection = getMysqlCon();
        printConnectInfo();
    }


}
