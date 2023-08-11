package group13.ecobikerental.controller;

import java.sql.SQLException;

import group13.ecobikerental.business_layer.BikeBL;
import group13.ecobikerental.dbconnnection_layer.BikeDL;
import group13.ecobikerental.entity.bike.Bike;

/**
 * This class controls the flow of events when users want to rent bike
 */
public class RentBikeController extends BaseController {

    /**
     * This method get bike info by Barcode
     * @param barcode - barcode of the bike
     * @return Bike information
     */
    public Bike viewBike(final String dockName, final String barcode) throws SQLException {

        String bikeCode = BikeBL.convertBarcodeToBikeCode(barcode);
        if (bikeCode != null) {
            return new BikeDL().getBikeByBikeCode(bikeCode, dockName);
        } else {
            return null;
        }
    }
//    public static void main(String[] args) {
//        Bike bike = null;
//        try {
//            bike = new RentBikeController().viewBike("Bach Khoa", "2278346617372");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(bike.toString());
//    }

//    test
    public static void main(String[] args) {
        Bike bike = null;
        try {
            bike = new RentBikeController().viewBike("Bach Khoa", "2278346617372");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bike.toString());
    }
}
