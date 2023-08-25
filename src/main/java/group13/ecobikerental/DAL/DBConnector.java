package group13.ecobikerental.DAL;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import group13.ecobikerental.utils.Configs;

/**
 * A utility class for connecting to the database.
 */
public final class DBConnector {
	private static final Logger LOGGER = getLogger(DBConnector.class.getName());
	private static Connection connection;
	private static DBConnector instance;

	/**
	 * Get the database connection.
	 * 
	 * @return The database connection.
	 */
	private DBConnector() {
	};

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

	public Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		try {
			String url = "jdbc:mysql://localhost:3306/" + Configs.DB_NAME;
			connection = DriverManager.getConnection(url, Configs.DB_USERNAME, Configs.DB_PASSWORD);
			LOGGER.log(Level.INFO, "Connected to the database.");
			return connection;
		} catch (SQLException throwables) {
//			throwables.printStackTrace();
			LOGGER.log(Level.SEVERE, "Failed to connect to the database.", throwables);
			return null;
		}
	}

	/**
	 * Close the database connection.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	private void closeConnection() throws SQLException {
		if (connection != null) {
			try {
				connection.close();
				LOGGER.log(Level.INFO, "Connection closed.");
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Error while closing the connection.", e);
			}
		}
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}
}
