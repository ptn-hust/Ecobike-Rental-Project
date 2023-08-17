package group13.ecobikerental.data_access_layer;

import java.sql.*;

import group13.ecobikerental.utils.Configs;

/**
 * A utility class for connecting to the database.
 */
public class DBConnector {
    private static Connection connect;

    /**
     * Get the database connection.
     * @return The database connection.
     */
    public static Connection getConnection() {
        if (connect != null) {
            return connect;
        }
        try {
        	String url = "jdbc:mysql://localhost:3306/";        	
        	url = url + Configs.DB_NAME;
            connect = DriverManager.getConnection(url, Configs.DB_USERNAME, Configs.DB_PASSWORD);
            System.out.println("connected");
            return connect;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Close the database connection.
     * @throws SQLException if a database access error occurs.
     */
    private static void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
}
