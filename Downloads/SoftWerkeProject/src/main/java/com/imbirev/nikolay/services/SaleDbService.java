package com.imbirev.nikolay.services;

import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;

public class SaleDbService {

    private Connection connection;

    SaleDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
    }

}
