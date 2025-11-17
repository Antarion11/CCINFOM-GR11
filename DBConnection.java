package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/modelkit_returns?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "071924"; // Change to your actual MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}