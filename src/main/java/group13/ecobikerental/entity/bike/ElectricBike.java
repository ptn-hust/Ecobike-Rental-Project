package group13.ecobikerental.entity.bike;

/**
 * Entity class for ElectricBike, which is a type of Bike.
 */
public class ElectricBike extends Bike {
	@Override
	public String toString() {
		return "ElectricBike [licensePlate=" + licensePlate + ", pin=" + pin + ", getType()=" + getType()
				+ ", getBarcode()=" + getBikecode() + ", getDockName()=" + getDockName() + ", getDeposit()="
				+ getDeposit() + ", getBikeCode()=" + getBikeCode() + ", toString()=" + super.toString()
				+ ", getBikeId()=" + getBikeId() + ", getIsBeingUsed()=" + getIsBeingUsed() + ", getBaseFee()="
				+ getBaseFee() + ", getExtraFee()=" + getExtraFee() + ", getDockId()=" + getDockId() + "]";
	}

	private String licensePlate;
	private int pin;

	/**
     * Default constructor for ElectricBike.
     */
	public ElectricBike() {
		super();
	}

	/**
     * Constructor for ElectricBike with specified attributes.
     * @param bikecode The bike code of the electric bike.
     * @param type The type of the electric bike.
     * @param dockName The name of the dock where the electric bike is located.
     * @param deposit The deposit amount for the electric bike.
     * @param licensePlate The license plate of the electric bike.
     * @param pin The PIN of the electric bike.
     */
	public ElectricBike(String bikecode, String type, String dockName, int deposit, String licensePlate, int pin) {
		super(bikecode, type, dockName, deposit);
		this.licensePlate = licensePlate;
		this.pin = pin;
	}

	/**
     * Constructor for ElectricBike with specified attributes.
     * @param bikecode The bike code of the electric bike.
     * @param type The type of the electric bike.
     * @param dockName The name of the dock where the electric bike is located.
     * @param deposit The deposit amount for the electric bike.
     * @param baseFee The base fee for renting the electric bike.
     * @param extraFee The extra fee for renting the electric bike.
     * @param licensePlate The license plate of the electric bike.
     * @param pin The PIN of the electric bike.
     */
	public ElectricBike(String bikecode, String type, String dockName, int deposit, int baseFee, int extraFee,
			String licensePlate, int pin) {
		super(bikecode, type, dockName, deposit, baseFee, extraFee);
		this.licensePlate = licensePlate;
		this.pin = pin;
	}

	/**
     * Gets the license plate of the electric bike.
     * @return The license plate.
     */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
     * Sets the license plate of the electric bike.
     * @param licensePlate The license plate to set.
     */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
     * Gets the PIN of the electric bike.
     * @return The PIN.
     */
	public int getPin() {
		return pin;
	}

	/**
     * Sets the PIN of the electric bike.
     * @param pin The PIN to set.
     */
	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	/**
     * Calculates the rental fee for the electric bike.
     * @param minutes The number of minutes rented.
     * @return The calculated rental fee.
     */
	public int calculateRentalFee(int minutes) {
		// TODO Auto-generated method stub
		return (int) (super.calculateRentalFee(minutes) * 1.5);
	}

	/**
     * For testing the ElectricBike class.
     * @param args Command line arguments.
     */
	public static void main(String[] args) {
		Bike bike = new ElectricBike("0987654321", "type 1", "dock", 20, "abcyxz", 80);
		System.out.println(bike.toString());
		System.out.println(((ElectricBike) bike).getPin());
	}

}
