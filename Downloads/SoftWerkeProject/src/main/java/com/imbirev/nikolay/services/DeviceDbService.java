package com.imbirev.nikolay.services;

import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;

// here the class for Sale connection
public class DeviceDbService {

    // necessary field of database information
    private Connection connection;

    // here we connect to the connection in the singleton
    DeviceDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
    }
}
