package group13.ecobikerental.dbconnnection_layer;

import java.sql.*;

import group13.ecobikerental.utils.Configs;

/**
 * This class controls the process of connecting to Database
 */
public class DBConnector {
    /**
     * Represent for connect
     */
    private static Connection connect;

    /**
     * This method gets connection to Database
     * @return connect or null
     */
    public static Connection getConnection() {
        if (connect != null) {
            return connect;
        }
        try {
//            String url = "jdbc:mysql://localhost:3306/ecobikerental";
        	String url = "jdbc:mysql://localhost:3306/";        	
            // them configs.DB_NAME
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
     * This method executes the connection query
     * @param sql - the sql query
     * @return result after executing query
     * @throws SQLException - Exceptions relate to SQL
     */
    public static ResultSet query(final String sql) throws SQLException {
        Statement statement = null;
        try {
            statement = DBConnector.getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
//
//    /**
//     * Process the connection
//     * @param args - the arguments
//     */
//    public static void main(String[] args) {
//        DBConnector.getConnection();
//    }
}
