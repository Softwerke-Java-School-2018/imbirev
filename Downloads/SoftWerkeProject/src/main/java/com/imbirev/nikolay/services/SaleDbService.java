package com.imbirev.nikolay.services;

import java.sql.Connection;

public class SaleDbService extends DbService {

    private Connection connection;

     SaleDbService() {
        connection = getMysqlCon();
        printConnectInfo();
    }

}
