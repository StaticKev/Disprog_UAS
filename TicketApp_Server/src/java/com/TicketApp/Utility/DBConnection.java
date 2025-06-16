package com.TicketApp.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/week13_160423020";
    private static final String host = "root";
    private static final String password = "#Staticuser646";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(jdbcUrl, host, password);
    }
}
