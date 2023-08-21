package group13.ecobikerental.service.bike;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.entity.bike.Bike;

public interface IBikeService {

	List<Bike> getBikeList();

	void updateBike(String bikeCode, String dockName, int option) throws SQLException;

	boolean validateBarcode(String barcode);

	String deduceBikeCode(String barcode);

	String convertBarcodeToBikeCode(String barcode);

	Bike getBikeByBikeCode(int dockId, String bikeCode) throws SQLException;

}
