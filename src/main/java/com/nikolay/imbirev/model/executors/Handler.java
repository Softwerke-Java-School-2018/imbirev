package com.nikolay.imbirev.model.executors;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Handler {
    void handle(ResultSet resultSet) throws SQLException;
}
