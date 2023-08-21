package group13.ecobikerental.service.bike;

import java.sql.SQLException;
import java.util.List;

import group13.ecobikerental.DAL.bike.IBikeDAL;
import group13.ecobikerental.entity.bike.Bike;

/**
 * Bike Business Logic class to validate and convert bike barcode
 */
public class BikeService implements IBikeService{
	// private static BikeService instance;
	private final IBikeDAL bikeDAL;
	private List<Bike> bikeList;

	public BikeService(IBikeDAL bikeDAL) throws SQLException {
		this.bikeDAL = bikeDAL;
	}

	/**
	 * @return the bikeList
	 */
	public List<Bike> getBikeList() {
		if (bikeList == null) {
			return this.bikeDAL.getBikeList();
		}
		return bikeList;
	}

	public void updateBike(String bikeCode, String dockName, int option) throws SQLException {
		this.bikeDAL.updateBike(bikeCode, dockName, option);
		return;
	}

	/**
	 * Validate a bike barcode.
	 * 
	 * @param barcode The barcode to validate.
	 * @return true if the barcode is valid, false otherwise.
	 */
	// barcode is a string of number, length 12
	public boolean validateBarcode(final String barcode) {
		if (barcode == null || barcode.length() != 12 || !barcode.matches("\\d{12}")) {
			return false;
		}
		return true;
	}

	/**
	 * Deduce the bike code from a barcode.
	 * 
	 * @param barcode The bike barcode
	 * @return The deduced bike code or null if not deducible.
	 */
	public String deduceBikeCode(String barcode) {
		String firstHalf = barcode.substring(0, 3);
		String secondHalf = barcode.substring(9, 12);

		if (secondHalf.equals(firstHalf)) {
			return firstHalf;
		}

		return null;
	}

	/**
	 * Convert a barcode to a bike code.
	 * 
	 * @param barcode The barcode to convert.
	 * @return The converted bike code or null if conversion fails.
	 */
	public String convertBarcodeToBikeCode(final String barcode) {
		if (!validateBarcode(barcode)) {
			System.out.println("Invalid barcode format");
			return null;
		}
		String bikecode = deduceBikeCode(barcode);
		return bikecode;
	}

	public Bike getBikeByBikeCode(int dockId, String bikeCode) throws SQLException {
		return this.bikeDAL.getBikeByBikeCode(dockId, bikeCode);
	}

}
