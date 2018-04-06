package com.imbirev.nikolay.services;

import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;

public class ClientDbService {

    private Connection connection;

    ClientDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
    }
}
