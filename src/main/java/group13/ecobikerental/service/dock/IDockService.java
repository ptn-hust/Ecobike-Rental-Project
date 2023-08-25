package group13.ecobikerental.service.dock;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.entity.dock.Dock;

public interface IDockService {

    List<Dock> searchDock(String name);

    List<Dock> getDockList();

    boolean checkDockAvailable(String dockName) throws SQLException;
}