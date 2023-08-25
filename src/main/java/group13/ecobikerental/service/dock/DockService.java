package group13.ecobikerental.service.dock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group13.ecobikerental.DAL.dock.IDockDAL;
import group13.ecobikerental.entity.dock.Dock;

public class DockService implements IDockService {
	private IDockDAL dockDAL;

	public DockService(IDockDAL dockDAL) throws SQLException {
		this.dockDAL = dockDAL;
	}

	/**
	 * Search for docks based on the dock name.
	 * 
	 * @param name The name to search for.
	 * @return A list of docks matching the search.
	 */
	public List<Dock> searchDock(final String name) {
		
		// update current list of dock first
		this.dockDAL.populateDockList();
		List<Dock> allDocks =  this.dockDAL.getDockList();
		// search dock
		List<Dock> list = new ArrayList<>();
		String lowercaseName = name.toLowerCase(); // Convert search string to lowercase

	    for (Dock dock : allDocks) {
	        if (dock.getDockName().toLowerCase().contains(lowercaseName) || 
	            dock.getAddress().toLowerCase().contains(lowercaseName)) {
	            list.add(dock);
	        }
	    }
	    return list;
	}
	
	public List<Dock> getDockList() {
		return this.dockDAL.getDockList();
	}	
	
	public boolean checkDockAvailable(final String dockName) throws SQLException {
		return this.dockDAL.checkDockAvailable(dockName);
	}
}
