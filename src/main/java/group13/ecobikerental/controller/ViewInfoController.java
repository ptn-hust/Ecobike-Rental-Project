package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;

/**
 * Controller class responsible for handling the view information related events.
 */
public class ViewInfoController extends BaseController {
	private DockDAL dockDlInstance;

	/**
     * Constructor for ViewInfoController.
     * @throws SQLException if a database access error occurs.
     */
	public ViewInfoController() throws SQLException {
		super();
		this.dockDlInstance = new DockDAL();
//		this.bikeDlInstance = new BikeDAL();
	}

	 /**
     * Retrieve a list of all docks.
     * @return A list of Dock objects.
     * @throws SQLException if a database access error occurs.
     */
	public List<Dock> getDockListRequest() throws SQLException {
		return dockDlInstance.getDockList();
	}

	/**
     * Search for docks by name.
     * @param name The name to search for.
     * @return A list of Dock objects matching the search criteria.
     * @throws SQLException if a database access error occurs.
     */
	public List<Dock> searchDockRequest(final String name) throws SQLException {
		return dockDlInstance.searchDock(name);
	}

//    public Bike getBikeRequest(final int dockId, final String barcode) throws SQLException {
//        String bikeCode = BikeBL.getInstance().convertBarcodeToBikeCode(barcode);
//        
//        System.out.println("hello final bikecode: " + bikeCode);
//        
//        if (bikeCode != null) {
//        	Bike biketest = this.dockDlInstance.getBikeByBikeCode(dockId, bikeCode);
//            System.out.println(biketest.getDeposit());
//        	return biketest;
//        } else {
//            return null;
//        }
//    }
}
