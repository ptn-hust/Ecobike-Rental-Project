package group13.ecobikerental.data_access_layer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.BikeFactory;
import group13.ecobikerental.entity.bike.ElectricBike;

/**
 * This class controls the database transactions relate to Bike
 */
public class BikeDAL {
    /**
     * Represent the list of bike
     */
    private List<Bike> bikeList;

    /**
     * Constructor for BikeDAL
     * @throws SQLException - Exceptions relate to SQL
     */
    public BikeDAL() throws SQLException {
        this.bikeList = new ArrayList<>();
        String sql =
            "select bike.bike_id as id, bike_barcode as code, \r\n"
            + "    biketype.bike_type_name as type,\r\n"
            + "    biketype.bike_type_deposit as deposit_fee,\r\n"
            + "    dock.dock_name as dock_name,\r\n"
            + "    dock.dock_id as dock_id,\r\n"
            + "    biketype.bike_type_base_fee as base_fee,\r\n"
            + "    biketype.bike_type_extra_fee as extra_fee \r\n"
            + "    from bike, dock, biketype where bike.dock = dock.dock_id and bike.bike_type = biketype.bike_type_id;";
        ResultSet res = DBConnector.query(sql);
        Bike bike;
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
            System.out.println(bike.toString());
            
            bikeList.add(bike);         
        }
    }

    /**
     * This method counts total of bikes in dock
     * @param dockName - name of dock
     * @return num - number of bikes
     * @throws SQLException - Exceptions relate to SQL
     */
    public static int countBikeInDock(String dockName) throws SQLException {
        int num = 0;
        String sql =
            "select count(bike.bike_id) as num from bike, dock where bike.dock = dock.dock_id and dock.dock_name = \'" + dockName +
                "\'";
        ResultSet res = DBConnector.query(sql);
        if (res.next()) {
            num = res.getInt("num");
        }
        return num;
    }

    /**
     * This method gets bike information from bike code
     * @param bikeCode - the bike code
     * @param dockName - the name of dock
     * @return result - Bike
     * @throws SQLException - Exceptions relate to SQL
     */
    public Bike getBikeByBikeCode(String bikeCode, int id) throws SQLException {
        Bike result = null;
        for (Bike bike : bikeList) {
            if (bike.getBikeCode().equals(bikeCode) && bike.getDockId() == id) {
                result = bike;
                break;
            }
        }
        if (result != null && result.getType().equals("Standard e-bike")) {
            ElectricBike eBike = (ElectricBike) result;
            String sql = "select ebike.* from bike,ebike where bike.bike_id = ebike.ebike_id and bike.bike_id = " + result.getBikeId();
            ResultSet res = DBConnector.query(sql);
            if (res.next()) {
                eBike.setPin(res.getInt("ebike_battery"));
                eBike.setLicensePlate(res.getString("ebike_license"));
            }
            return eBike;
        }
        return result;
    }

    /**
     * This method updates 'dock' in Bike database when user rent/return a bike
     * @param bikeCode - the bike code
     * @param dockName - the name of dock
     * @param option - equals 1 in case of rent bike, 0 otherwise
     * @throws SQLException - Exceptions relate to SQL
     */
    public void updateBike(String bikeCode, String dockName, int option) throws SQLException {
        int dockId = 0;
        if (dockName != null) {
            dockId = DockDAL.getInstance().getDockId(dockName);
            if (dockId == 0) {
                throw new SQLException();
            }
        }
        String sql = "";
        if (option == 1) {
        	sql = "UPDATE bike SET bike_is_being_used = 1, dock = ?  WHERE bike_barcode = ?";
        } else{
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
}
