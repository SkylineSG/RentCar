package com.db;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {
    private Connection conn;
    private static DbManager dbManagerInstance;

    @Value("${DbPassword}")
    private String dbPassword;

    private DbManager() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "carRent_user");
        connectionProps.put(dbPassword);
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rentCars?serverTimezone=Europe/Warsaw" +
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

