package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static DBConnection INSTANCE = new DBConnection();
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/eshop_1";
    private final String USERNAME = "root";
    private final String PASSWORD = "Ashot2580456";

    private DBConnection() {
    }

    public static DBConnection getINSTANCE() {
        return INSTANCE;
    }

    public   Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
