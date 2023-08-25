package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.service.dock.IDockService;

public class ViewInfoController extends BaseController {
	private IDockService dockServiceInstance;

	public ViewInfoController(IDockService dockServiceInstance) throws SQLException {
		super();
		this.dockServiceInstance = dockServiceInstance;
	}

	public List<Dock> getDockListRequest() throws SQLException {
		return dockServiceInstance.getDockList();
	}

	public List<Dock> searchDockRequest(final String name) throws SQLException {
		return dockServiceInstance.searchDock(name);
	}
}
