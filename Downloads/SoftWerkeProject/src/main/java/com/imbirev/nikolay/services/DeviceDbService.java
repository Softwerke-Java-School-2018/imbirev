package com.imbirev.nikolay.services;

import com.imbirev.nikolay.singletons.ServiceSingleton;

import java.sql.Connection;

public class DeviceDbService {

    private Connection connection;

    DeviceDbService() {
        connection = ServiceSingleton.getsServiceSingleton().getConnection();
    }
}
