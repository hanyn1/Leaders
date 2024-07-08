package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConfig {
    private static final String URL = "jdbc:mysql://localhost:3307/evolearn";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private Connection connection;
    private static MyConfig instance;

    private MyConfig() {
        try {
            Class.forName(DRIVER_CLASS);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection established!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static MyConfig getInstance() {
        if (instance == null) {
            instance = new MyConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
