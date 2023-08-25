package group13.ecobikerental.DAL.dock;

import java.util.List;

import group13.ecobikerental.entity.dock.Dock;

public interface IDockDAL {

	void populateDockList();

	List<Dock> getDockList();

	boolean checkDockAvailable(String dockName);
}
