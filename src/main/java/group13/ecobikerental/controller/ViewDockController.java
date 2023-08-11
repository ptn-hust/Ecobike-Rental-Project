package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.dbconnnection_layer.BikeDL;
import group13.ecobikerental.dbconnnection_layer.DockDL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;

/**
 * This class controls the flow of events when users view the Dock
 */
public class ViewDockController extends BaseController {
	private DockDL dockDlInstance;

	public ViewDockController() throws SQLException {
		super();
		this.dockDlInstance = new DockDL();
	}

	/**
	 * This method get the list of docks
	 * 
	 * @return List of docks
	 * @throws SQLException - Exceptions relate to SQL
	 */
	public List<Dock> getDockList() throws SQLException {
//        return DockDL.getInstance().getDockList();
		return dockDlInstance.getDockList();
	}

	/**
	 * This method get info dock by id
	 * 
	 * @param name - letters in name of dock
	 * @return dock has letters of dock name like param
	 * @throws SQLException - Exceptions relate to SQL
	 */
	public List searchDock(final String name) throws SQLException {
//        return DockDL.getInstance().searchDock(name);
		return dockDlInstance.searchDock(name);
	}

	/**
	 * This method gets number of bikes in a dock
	 * 
	 * @param dockName - name of dock
	 * @return number of bikes in dock
	 * @throws SQLException - Exceptions relate to SQL
	 */
//    public int getNumberOfBike(String dockName) throws SQLException {
////        return BikeDL.countBikeInDock(dockName)
//    	return DockDL.countAvailableBike(dockName);
//    }

	/*
	 * method lay tu ben rentbikeController
	 */
//	public Bike viewBike(final String dockName, final String barcode) throws SQLException {
//
//		String bikeCode = BikeBL.convertBarcodeToBikeCode(barcode);
//		if (bikeCode != null) {
//			return new BikeDL().getBikeByBikeCode(bikeCode, dockName);
//		} else {
//			return null;
//		}
//	}
}
