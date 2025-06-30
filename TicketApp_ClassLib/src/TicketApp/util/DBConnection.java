package TicketApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/isadp";
    private static final String host = "root";
    private static final String password = "";
    
    private DBConnection() {}
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, host, password);
    }
}