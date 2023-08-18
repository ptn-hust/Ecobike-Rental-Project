package group13.ecobikerental.data_access_layer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.BikeFactory;
import group13.ecobikerental.entity.bike.ElectricBike;

/**
 * Data Access Layer for managing Bike data.
 */
public class BikeDAL {

	private List<Bike> bikeList;

	/**
     * Constructor for BikeDAL.
     * @throws SQLException if a database access error occurs.
     */
	public BikeDAL() throws SQLException {
		this.bikeList = new ArrayList<>();

		Statement stmt = DBConnector.getConnection().createStatement();
		// select all bike in dock given dock id
		String query = "select bike.bike_id as id, bike_barcode as code, \r\n"
				+ "    biketype.bike_type_name as type,\r\n" + "    biketype.bike_type_deposit as deposit_fee,\r\n"
				+ "    dock.dock_name as dock_name,\r\n" + "	bike.dock as dock_id,\r\n"
				+ "    biketype.bike_type_base_fee as base_fee,\r\n"
				+ "    biketype.bike_type_extra_fee as extra_fee \r\n"
				+ "    from bike, dock, biketype where bike.bike_type = biketype.bike_type_id and bike.dock = dock.dock_id";
		ResultSet res = stmt.executeQuery(query);

		Bike bike = null;
		while (res.next()) {
			bike = new BikeFactory().getBike(res.getString("type"));
			bike.setBikeId(res.getInt("id"));
			bike.setBikeCode(res.getString("code"));
			bike.setType(res.getString("type"));
			bike.setDockName(res.getString("dock_name"));
			bike.setDockId(res.getInt("dock_id"));
			bike.setDeposit(res.getInt("deposit_fee"));
			bike.setBaseFee(res.getInt("base_fee"));
			bike.setExtraFee(res.getInt("extra_fee"));
			if(res.getString("type").equals("Standard e-bike")) {
				
			}
			System.out.println(bike.toString());

			bikeList.add(bike);
		}
	}

	/**
     * Update the bike's information.
     * @param bikeCode The code of the bike.
     * @param dockName The name of the dock.
     * @param option   1 for being used, 0 for not being used.
     * @throws SQLException if a database access error occurs.
     */
	public void updateBike(String bikeCode, String dockName, int option) throws SQLException {
		int dockId = 0;
		if (dockName != null) {
			dockId = new DockDAL().getDockId(dockName);
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
			pstmt = DBConnector.getConnection().prepareStatement(sql);

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
     * @return A list of Bike objects.
     */
	public List<Bike> getBikeList() {
		return bikeList;
	}

}
