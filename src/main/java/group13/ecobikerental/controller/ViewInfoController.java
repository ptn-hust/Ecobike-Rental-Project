package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;

/**
 * This class controls the flow of events when users view the Dock
 */
public class ViewInfoController extends BaseController {
	private DockDAL dockDlInstance;
	private BikeDAL bikeDlInstance;

	public ViewInfoController() throws SQLException {
		super();
		this.dockDlInstance = new DockDAL();
		this.bikeDlInstance = new BikeDAL();
	}

	public List<Dock> getDockListRequest() throws SQLException {
		return dockDlInstance.getDockList();
	}

	public List<Dock> searchDockRequest(final String name) throws SQLException {
		return dockDlInstance.searchDock(name);
	}

    public Bike getBikeRequest(final int id, final String barcode) throws SQLException {

        String bikeCode = BikeBL.convertBarcodeToBikeCode(barcode);
        if (bikeCode != null) {
            return this.bikeDlInstance.getBikeByBikeCode(bikeCode, id);
        } else {
            return null;
        }
    }
}
