package com.db;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {

    @Value("{DbPassword}")
    private static String PASSWORD;
    @Value("{DbUsername}")
    private static String USERNAME;

    private Connection conn;
    private static DbManager dbManagerInstance;

    private DbManager() throws SQLException {
        Properties connectionProps = new Properties();

        connectionProps.put("user", USERNAME);
        connectionProps.put("password", PASSWORD);
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rentalDB?serverTimezone=Europe/Warsaw" +
                        "&useSSL=False",
                connectionProps);
    }

    private static DbManager getInstance() throws SQLException {
        if (dbManagerInstance == null) {
            dbManagerInstance = new DbManager();
        }
        return dbManagerInstance;
    }

    private Connection getConnection() {
        return conn;
    }
}

