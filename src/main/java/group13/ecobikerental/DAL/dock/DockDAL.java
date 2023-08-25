package group13.ecobikerental.DAL.dock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import group13.ecobikerental.DAL.DBConnector;
import group13.ecobikerental.entity.dock.Dock;

/**
 * Data Access Layer class for managing dock-related database operations.
 */
public class DockDAL implements IDockDAL{
	private static final Logger LOGGER = Logger.getLogger(DockDAL.class.getName());
	private List<Dock> dockList;

	/**
	 * Constructor to initialize DockDAL and populate the dockList from the
	 * database.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	public DockDAL() throws SQLException {
		populateDockList();
	}

	public void populateDockList() {
		this.dockList = new ArrayList<>();
		try (Statement stmt = DBConnector.getInstance().getConnection().createStatement()) {
			String query = "SELECT dock.*, COUNT(bike.bike_id) AS available_bike FROM dock\n"
					+ "LEFT JOIN bike ON bike.dock = dock.dock_id AND bike.bike_is_being_used = 0\n"
					+ "GROUP BY dock.dock_id;";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				Dock dock = new Dock(res.getInt("dock_id"), res.getString("dock_name"), res.getString("dock_address"),
						res.getInt("dock_total_bike"), res.getInt("dock_area"), res.getInt("available_bike"),
						res.getString("dock_img_url"));
				dockList.add(dock);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error populating dock list.", e);
		}
	}

	/**
	 * Get the list of docks.
	 * 
	 * @return The list of docks.
	 */
	public List<Dock> getDockList() {
		return dockList;
	}

	/**
	 * Check if a dock has available space for bikes.
	 * 
	 * @param dockName The name of the dock.
	 * @return True if the dock has available space, false otherwise.
	 */
	public boolean checkDockAvailable(final String dockName) {
		int totalBike = 0;
		int availableBike = 0;
		String query = "SELECT " + "    d.dock_id, d.dock_name, " + "    d.dock_total_bike,\r\n"
				+ "    IFNULL(COUNT(b.bike_id), 0) AS available_bike_count\r\n" + "FROM\r\n" + "    Dock d\r\n"
				+ "LEFT JOIN\r\n" + "    Bike b ON d.dock_id = b.dock AND b.bike_is_being_used = 0\r\n" + "WHERE\r\n"
				+ "    d.dock_name = \"" + dockName + "\" GROUP BY\r\n" + "    d.dock_id;";

		try (Statement stmt = DBConnector.getInstance().getConnection().createStatement()) {
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				totalBike = res.getInt("dock_total_bike");
				availableBike = res.getInt("available_bike_count");
			}

			return availableBike < totalBike;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
