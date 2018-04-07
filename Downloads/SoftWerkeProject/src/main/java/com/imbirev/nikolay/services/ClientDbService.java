package com.imbirev.nikolay.services;

import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;

// here the class for Client connection
public class ClientDbService {

    // necessary field of database information
    private Connection connection;

    // here we connect to the connection in the sigleton
    ClientDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
    }
}
