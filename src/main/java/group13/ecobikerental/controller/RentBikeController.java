package group13.ecobikerental.controller;

import java.sql.SQLException;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.service.bike.IBikeService;

/**
 * Controller responsible for handling bike rental-related actions.
 */
public class RentBikeController extends BaseController {
	private IBikeService bikeServiceInstance;

	/**
     * Constructs a new RentBikeController instance.
     * @throws SQLException if a database access error occurs.
     */
	public RentBikeController(IBikeService bikeServiceInstance) throws SQLException {
		super();
		this.bikeServiceInstance = bikeServiceInstance;
	}

	public String getBikeCodeRequest(String barcode) throws SQLException {
		String bikeCode = this.bikeServiceInstance.convertBarcodeToBikeCode(barcode);
		return bikeCode;
	}

	public Bike getBikeRequest(int dockId, String bikeCode) throws SQLException {
		Bike bike;
		bike = this.bikeServiceInstance.getBikeByBikeCode(dockId, bikeCode);
		return bike;
	}
}
