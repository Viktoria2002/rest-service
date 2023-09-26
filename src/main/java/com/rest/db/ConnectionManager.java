package com.rest.db;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static com.rest.util.Constants.DatabaseSetup.*;
import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public class ConnectionManager {
    private static HikariDataSource dataSource;

    private ConnectionManager() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    static {
        dataSource = new HikariDataSource();
        dataSource.setDriverClassName(PropertyManager.getProperty(DRIVER));
        dataSource.setJdbcUrl(PropertyManager.getProperty(URL));
        dataSource.setUsername(PropertyManager.getProperty(USERNAME));
        dataSource.setPassword(PropertyManager.getProperty(PASSWORD));
        dataSource.setMaximumPoolSize(30);

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnectionPool() {
        dataSource.close();
    }
}
