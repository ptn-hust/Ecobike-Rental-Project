package group13.ecobikerental.entity.bike;

/**
 * Entity Bike
 */
public class Bike {
	private int bikeId;

	private String bikeCode;

	private String type;

	private String dockName;

	private int dockId;

	private int deposit;

	private String brand;

	private int isBeingUsed;

	private int baseFee;

	private int extraFee;

	/**
	 * Default constructor for creating a Bike instance.
	 */
	public Bike() {

	}

	/**
	 * Constructor for creating a Bike instance with specified parameters.
	 * 
	 * @param bikeCode The code of the bike.
	 * @param type     The type of the bike.
	 * @param dockName The name of the dock where the bike is located.
	 * @param deposit  The deposit fee of the bike.
	 */
	public Bike(String bikecode, String type, String dockName, int deposit) {
		this.bikeCode = bikecode;
		this.type = type;
		this.dockName = dockName;
		this.deposit = deposit;
		this.baseFee = 10000;
		this.extraFee = 3000;
	}

	/**
	 * Constructor for creating a Bike instance with all parameters.
	 * 
	 * @param bikeCode The code of the bike.
	 * @param type     The type of the bike.
	 * @param dockName The name of the dock where the bike is located.
	 * @param deposit  The deposit fee of the bike.
	 * @param baseFee  The base rental fee of the bike.
	 * @param extraFee The extra rental fee of the bike.
	 */
	public Bike(String bikeCode, String type, String dockName, int deposit, int baseFee, int extraFee) {
		super();
		this.bikeCode = bikeCode;
		this.type = type;
		this.dockName = dockName;
		this.deposit = deposit;
		this.baseFee = baseFee;
		this.extraFee = extraFee;
	}

	/**
	 * Gets the type of the bike.
	 * 
	 * @return The type of the bike.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the bike.
	 * 
	 * @param type The type of the bike.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the code of the bike.
	 * 
	 * @return The code of the bike.
	 */
	public String getBikecode() {
		return this.bikeCode;
	}

	/**
	 * Gets the name of the dock where the bike is located.
	 * 
	 * @return The name of the dock.
	 */
	public String getDockName() {
		return dockName;
	}

	/**
	 * Sets the name of the dock where the bike is located.
	 * 
	 * @param dockName The name of the dock.
	 */
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	/**
	 * Gets the deposit fee of the bike.
	 * 
	 * @return The deposit fee.
	 */
	public int getDeposit() {
		return deposit;
	}

	/**
	 * Sets the deposit fee of the bike.
	 * 
	 * @param deposit The deposit fee.
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	/**
	 * Gets the code of the bike.
	 * 
	 * @return The code of the bike.
	 */
	public String getBikeCode() {
		return bikeCode;
	}

	/**
	 * Sets the code of the bike.
	 * 
	 * @param bikeCode The code of the bike.
	 */
	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	@Override
	public String toString() {
		return "Bike [bikeId=" + bikeId + ", bikeCode=" + bikeCode + ", type=" + type + ", dockName=" + dockName
				+ ", deposit=" + deposit + ", isBeingUsed=" + isBeingUsed + ", baseFee=" + baseFee + ", extraFee="
				+ extraFee + "]";
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public int getIsBeingUsed() {
		return isBeingUsed;
	}

	public void setIsBeingUsed(int isBeingUsed) {
		this.isBeingUsed = isBeingUsed;
	}

	public int getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(int baseFee) {
		this.baseFee = baseFee;
	}

	public int getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(int extraFee) {
		this.extraFee = extraFee;
	}

	public int getDockId() {
		return dockId;
	}

	public void setDockId(int dockId) {
		this.dockId = dockId;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Calculates the rental fee based on the number of minutes.
	 * 
	 * @param minutes The number of minutes for the rental.
	 * @return The calculated rental fee.
	 */
	public int calculateRentalFee(int minutes) {
		int rentalFee = 0;
		if (minutes <= 1) {
			rentalFee = 0;
		} else if (minutes < 30) {
			rentalFee = this.baseFee;
		} else if (minutes % 15 == 0) {
			rentalFee = this.baseFee + (minutes - 30) / 15 * this.extraFee;
		} else {
			rentalFee = this.baseFee + ((minutes - 30) / 15 + 1) * this.extraFee;
		}
		return rentalFee;
	}
}
