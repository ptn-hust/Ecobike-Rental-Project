package group13.ecobikerental.dbconnnection_layer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import group13.ecobikerental.entity.dock.Dock;

/**
 * This class controls the database transactions relate to Dock
 */
public class DockDL {
    /**
     * Represent the list of docks
     */
    private List<Dock> dockList;

    /**
     * Represent the instance of dock
     */
    private static DockDL instance;

    /**
     * Constructor for DockDL
     * @throws SQLException - Exceptions relate to SQL
     */
    public DockDL() throws SQLException {
        this.dockList = new ArrayList<>();
        Statement stmt = DBConnector.getConnection().createStatement();
        String query = "select dock.*, COUNT(bike.bike_id) as available_bike from dock\r\n"
        		+ "left join bike on bike.dock = dock.dock_id and bike.bike_is_being_used = 0\r\n"
        		+ "group by dock.dock_id;";
        ResultSet res = stmt.executeQuery(query);
        Dock dock;
        while (res.next()) {
            dock = new Dock(res.getInt("dock_id"), res.getString("dock_name"), res.getString("dock_address"), res.getInt("dock_total_bike"),
                res.getInt("dock_area"), res.getInt("available_bike"), res.getString("dock_img_url"));
            dockList.add(dock);
        }
    }

    /**
     * This method gets list of docks
     * @return dockList - the list of docks
     */
    public List<Dock> getDockList() {
        return dockList;
    }


    /**
     * This method is creating for searching dock
     * @param name - the name of dock that user want to search
     * @return list - list of dock(s) after searching
     */
    public List<Dock> searchDock(final String name) {
        List<Dock> list = new ArrayList<>();
        for (Dock dock : dockList) {
            if (dock.getDockName().contains(name)) {
                list.add(dock);
            }
        }
        return list;
    }

    /**
     * This method checks the availability of dock
     * @param dockName - the name of dock
     * @return true if the dock is valid, false if not
     * @throws SQLException - Exceptions relate to SQL
     */
    public boolean checkDockAvailable(final String dockName) throws SQLException {
        int quantity = 0;
        for (Dock dock : dockList) {
            if (dock.getDockName().equals(dockName)) {
                quantity = dock.getTotal_bike();
                break;
            }
        }
        if ( BikeDL.countBikeInDock(dockName) < quantity) {
            return true;
        } else {
            return false;
        }
    }
    public static int countAvailableBike(String dockName) throws SQLException {
    	int available_bike = 0;
        String sql =
            "select count(bike.bike_id) as available_bike from bike, dock where bike.dock = dock.dock_id and bike.bike_is_being_used = 0 and dock.dock_name = \'" + dockName +
                "\'";
        ResultSet res = DBConnector.query(sql);
        if (res.next()) {
        	available_bike = res.getInt("available_bike");
        	System.out.println(available_bike);
        }
        return available_bike;
    }

    /**
     * This method gets instance of DockDL
     * @return instance - the instance of DockDL
     * @throws SQLException - Exceptions relate to SQL
     */
    public static DockDL getInstance() throws SQLException {
        if (instance == null) {
            return new DockDL();
        }
        return instance;
    }

    /**
     * This method gets id of dock
     * @param dockName - the name of dock
     * @return id - the id of dock or 0
     */
    public int getDockId(String dockName) {
        for (Dock dock : dockList) {
            if (dock.getDockName().equals(dockName)) {
                return dock.getId();
            }
        }
        return 0;
    }
}
