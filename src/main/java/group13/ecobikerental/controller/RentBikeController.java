package group13.ecobikerental.controller;

import java.sql.SQLException;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.entity.bike.Bike;
/**
 * Controller responsible for handling bike rental-related actions.
 */
public class RentBikeController extends BaseController {
	private DockDAL dockDlInstance;

	/**
     * Constructs a new RentBikeController instance.
     * @throws SQLException if a database access error occurs.
     */
	public RentBikeController() throws SQLException {
		super();
		this.dockDlInstance = new DockDAL();
	}

	public String getBikeCodeRequest(String barcode) {
		String bikeCode = BikeBL.getInstance().convertBarcodeToBikeCode(barcode);
		return bikeCode;
	}

	/**
     * Retrieves a bike from a dock based on its bike code.
     * @param dockId   The ID of the dock.
     * @param bikeCode The code of the bike.
     * @return The Bike object retrieved from the dock.
     * @throws SQLException if a database access error occurs.
     */
	public Bike getBikeRequest2(int dockId, String bikeCode) throws SQLException {
		Bike biketest;
		biketest = this.dockDlInstance.getBikeByBikeCode(dockId, bikeCode);
		return biketest;
	}
}
