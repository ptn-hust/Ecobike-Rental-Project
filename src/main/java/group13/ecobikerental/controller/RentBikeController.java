package group13.ecobikerental.controller;

import java.sql.SQLException;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.entity.bike.Bike;

public class RentBikeController extends BaseController {
	private DockDAL dockDlInstance;

	public RentBikeController() throws SQLException {
		super();
		this.dockDlInstance = new DockDAL();
	}

//	public Bike getBikeRequest(final int dockId, String barcode) throws SQLException {
//		String bikeCode = BikeBL.getInstance().convertBarcodeToBikeCode(barcode);
//		if (bikeCode != null) {
//			Bike biketest = this.dockDlInstance.getBikeByBikeCode(dockId, bikeCode);
//			return biketest;
//		} else {
//			return null;
//		}
//	}

	public String getBikeCodeRequest(String barcode) {
		String bikeCode = BikeBL.getInstance().convertBarcodeToBikeCode(barcode);
		return bikeCode;
	}

	public Bike getBikeRequest2(int dockId, String bikeCode) throws SQLException {
		Bike biketest;
		biketest = this.dockDlInstance.getBikeByBikeCode(dockId, bikeCode);
		return biketest;
	}
}
