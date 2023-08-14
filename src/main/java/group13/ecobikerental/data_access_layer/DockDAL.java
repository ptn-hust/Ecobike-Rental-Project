package group13.ecobikerental.data_access_layer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.BikeFactory;
import group13.ecobikerental.entity.dock.Dock;

public class DockDAL {

	private List<Dock> dockList;

	public DockDAL() throws SQLException {
		// initialize a dockList
		this.dockList = new ArrayList<>();

		Statement stmt = DBConnector.getConnection().createStatement();
		String query = "select dock.*, COUNT(bike.bike_id) as available_bike from dock\r\n"
				+ "left join bike on bike.dock = dock.dock_id and bike.bike_is_being_used = 0\r\n"
				+ "group by dock.dock_id;";
		ResultSet res = stmt.executeQuery(query);

		Dock dock = null;
		while (res.next()) {
			dock = new Dock(res.getInt("dock_id"), res.getString("dock_name"), res.getString("dock_address"),
					res.getInt("dock_total_bike"), res.getInt("dock_area"), res.getInt("available_bike"),
					res.getString("dock_img_url"));
			dockList.add(dock);
		}
	}

	public List<Dock> getDockList() {
		return dockList;
	}

	public List<Dock> searchDock(final String name) {
		List<Dock> list = new ArrayList<>();
		for (Dock dock : dockList) {
			if (dock.getDockName().contains(name)) {
				list.add(dock);
			}
		}
		return list;
	}

	public boolean checkDockAvailable(final String dockName) throws SQLException {
		int total_bike = 0;

		Statement stmt = DBConnector.getConnection().createStatement();
		String query = "select dock_total_bike from dock where dock.dock_name = \'" + dockName + "\'";
		ResultSet res = stmt.executeQuery(query);

		if (res.next()) {
			total_bike = res.getInt("dock_total_bike");
		}

		if (this.countAvailableBike(dockName) < total_bike) {
			return true;
		} else {
			return false;
		}

	}

	public int countAvailableBike(String dockName) throws SQLException {
		int available_bike = 0;

		Statement stmt = DBConnector.getConnection().createStatement();
		String query = "select count(bike.bike_id) as available_bike from bike, dock where bike.dock = dock.dock_id and bike.bike_is_being_used = 0 and dock.dock_name = \'"
				+ dockName + "\'";
		ResultSet res = stmt.executeQuery(query);

		if (res.next()) {
			available_bike = res.getInt("available_bike");
			System.out.println(available_bike);
		}
		return available_bike;
	}

	public int getDockId(String dockName) {
		for (Dock dock : dockList) {
			if (dock.getDockName().equals(dockName)) {
				return dock.getId();
			}
		}
		return 0;
	}

	public Bike getBikeByBikeCode(int dockId, String bikeCode) throws SQLException {
		Statement stmt = DBConnector.getConnection().createStatement();
		String query = "select bike.bike_id as id, bike.bike_barcode as bikecode,\r\n"
				+ " biketype.bike_type_name as bike_type,\r\n" + " bike.dock as dock_id,\r\n"
				+ " dock.dock_name as dock_name, \r\n" + " biketype.bike_type_base_fee as base_fee,\r\n"
				+ " biketype.bike_type_deposit as deposit,\r\n" + " biketype.bike_type_extra_fee as extra_fee\r\n"
				+ "from bike join dock on bike.dock = dock.dock_id join biketype on bike.bike_type = biketype.bike_type_id \r\n"
				+ " where bike.dock = " + dockId + " and bike.bike_barcode = " + bikeCode;
		System.out.println(query);

		ResultSet res = stmt.executeQuery(query);
		if (res.next()) {
			System.out.println("hello1111" + res.getString("bike_type"));
			Bike bike = null;
			bike = new BikeFactory().getBike(res.getString("bike_type"));
			bike.setBikeId(res.getInt("id"));
			bike.setBikeCode(res.getString("bikecode"));

			bike.setType(res.getString("bike_type"));

			bike.setDockId(res.getInt("dock_id"));
			bike.setDockName(res.getString("dock_name"));

			bike.setDeposit(res.getInt("deposit"));
			bike.setBaseFee(res.getInt("base_fee"));
			bike.setExtraFee(res.getInt("extra_fee"));

			System.out.println("dockDAL" + bike.getBaseFee());
			return bike;
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		DockDAL instance = new DockDAL();
		instance.getBikeByBikeCode(5, "666");
		System.out.println("complete");
	}
}
