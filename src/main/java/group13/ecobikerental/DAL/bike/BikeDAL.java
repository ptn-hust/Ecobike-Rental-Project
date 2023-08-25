package group13.ecobikerental.DAL.bike;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import group13.ecobikerental.DAL.DBConnector;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.BikeFactory;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.dock.Dock;

/**
 * Data Access Layer for managing Bike data.
 */
public class BikeDAL implements IBikeDAL{
	private static final Logger LOGGER = Logger.getLogger(BikeDAL.class.getName());

	private List<Bike> bikeList;
	private List<Dock> dockList;

	/**
	 * Constructor for BikeDAL.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	public BikeDAL() throws SQLException {
		populateBikeList();
		populateDockList();
	}

	private void populateBikeList() {
		this.bikeList = new ArrayList<>();
		try (Statement stmt = DBConnector.getInstance().getConnection().createStatement()) {
			String query = "SELECT bike.bike_id AS id, bike_barcode AS code, " + "biketype.bike_type_name AS type, "
					+ "biketype.bike_type_deposit AS deposit_fee, " + "dock.dock_name AS dock_name, "
					+ "bike.dock AS dock_id, " + "biketype.bike_type_base_fee AS base_fee, "
					+ "biketype.bike_type_extra_fee AS extra_fee " + "FROM bike, dock, biketype "
					+ "WHERE bike.bike_type = biketype.bike_type_id AND bike.dock = dock.dock_id";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				Bike bike = new BikeFactory().getBike(res.getString("type"));
				bike.setBikeId(res.getInt("id"));
				bike.setBikeCode(res.getString("code"));
				bike.setType(res.getString("type"));
				bike.setDockName(res.getString("dock_name"));
				bike.setDockId(res.getInt("dock_id"));
				bike.setDeposit(res.getInt("deposit_fee"));
				bike.setBaseFee(res.getInt("base_fee"));
				bike.setExtraFee(res.getInt("extra_fee"));
				if (res.getString("type").equals("Standard e-bike")) {
					// If you need to do something specific for "Standard e-bike", you can do it
					// here
				}

				bikeList.add(bike);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void populateDockList() {
		this.dockList = new ArrayList<>();
		try (Statement stmt = DBConnector.getInstance().getConnection().createStatement()) {
			String query = "SELECT dock.*, COUNT(bike.bike_id) AS available_bike FROM dock\n"
					+ "LEFT JOIN bike ON bike.dock = dock.dock_id AND bike.bike_is_being_used = 0\n"
					+ "GROUP BY dock.dock_id;";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				Dock dock = new Dock(res.getInt("dock_id"), res.getString("dock_name"), res.getString("dock_address"),
						res.getInt("dock_total_bike"), res.getInt("dock_area"), res.getInt("available_bike"),
						res.getString("dock_img_url"));
				dockList.add(dock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error populating dock list.", e);
		}
	}

	/**
	 * Update the bike's information.
	 * 
	 * @param bikeCode The code of the bike.
	 * @param dockName The name of the dock.
	 * @param option   1 for being used, 0 for not being used.
	 * @throws SQLException if a database access error occurs.
	 */
	// nen tach thanh 2 function
	public void updateBike(String bikeCode, String dockName, int option) throws SQLException {
		System.out.println("stuck here: " + bikeCode + " " + dockName + " " + option);
		int dockId = 0;
		if (dockName != null) {
			populateDockList();
			for (Dock dock : dockList) {
				if (dock.getDockName().equals(dockName)) {
					dockId = dock.getId();
					System.out.println("vo day ne: " + dockId);
				}
			}
			if (dockId == 0) {
				throw new SQLException();
			}
		}
		String sql = "";
		if (option == 1) {
			sql = "UPDATE bike SET bike_is_being_used = 1, dock = ?  WHERE bike_barcode = ?";
		} else {
			sql = "UPDATE bike SET bike_is_being_used = 0, dock = ?  WHERE bike_barcode = ?";
		}

		PreparedStatement pstmt = null;
		try {
			pstmt = DBConnector.getInstance().getConnection().prepareStatement(sql);

			if (dockName != null) {
				pstmt.setString(1, String.valueOf(dockId));
			} else {
				pstmt.setString(1, null);
			}
			pstmt.setString(2, bikeCode);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Get the list of bikes.
	 * 
	 * @return A list of Bike objects.
	 */
	public List<Bike> getBikeList() {
		return bikeList;
	}

	public Bike getBikeByBikeCode(int dockId, String bikeCode) throws SQLException {
		Statement stmt = DBConnector.getInstance().getConnection().createStatement();
		String query = "select bike.bike_id as id, bike.bike_barcode as bikecode,\r\n"
				+ " biketype.bike_type_name as bike_type,\r\n" + " bike.dock as dock_id,\r\n"
				+ " dock.dock_name as dock_name, \r\n" + " biketype.bike_type_base_fee as base_fee,\r\n"
				+ " biketype.bike_type_deposit as deposit,\r\n" + " biketype.bike_type_extra_fee as extra_fee\r\n"
				+ "from bike join dock on bike.dock = dock.dock_id join biketype on bike.bike_type = biketype.bike_type_id \r\n"
				+ " where bike.dock = " + dockId + " and bike.bike_barcode = " + bikeCode;
		System.out.println(query);

		ResultSet res = stmt.executeQuery(query);
		if (res.next()) {
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

			if (res.getString("bike_type").equals("Standard e-bike")) {
				ElectricBike eBike = (ElectricBike) bike;
				String sql2 = "select * from ebike where ebike_id = " + bike.getBikeId();
				ResultSet res1 = stmt.executeQuery(sql2);
				if (res1.next()) {
					eBike.setLicensePlate(res1.getString("ebike_license"));
					eBike.setPin(res1.getInt("ebike_battery"));
				}
				return eBike;
			}

			System.out.println("dockDAL" + bike.getBaseFee());
			return bike;
		}
		return null;
	}

}
