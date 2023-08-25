package group13.ecobikerental.DAL.bike;

import java.sql.SQLException;
import java.util.List;
import group13.ecobikerental.entity.bike.Bike;

/**
 * Interface for managing Bike data.
 */
public interface IBikeDAL {

	/**
	 * Update the bike's information.
	 * 
	 * @param bikeCode The code of the bike.
	 * @param dockName The name of the dock.
	 * @param option   1 for being used, 0 for not being used.
	 * @throws SQLException if a database access error occurs.
	 */
	void updateBike(String bikeCode, String dockName, int option) throws SQLException;

	/**
	 * Get the list of bikes.
	 * 
	 * @return A list of Bike objects.
	 */
	List<Bike> getBikeList();

	/**
	 * Get a bike by its bike code and dock ID.
	 * 
	 * @param dockId   The ID of the dock.
	 * @param bikeCode The bike code.
	 * @return The Bike object if found, otherwise null.
	 * @throws SQLException if a database access error occurs.
	 */
	Bike getBikeByBikeCode(int dockId, String bikeCode) throws SQLException;
}
