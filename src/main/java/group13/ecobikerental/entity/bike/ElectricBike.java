package group13.ecobikerental.entity.bike;

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

	public ElectricBike() {
		super();
	}

	public ElectricBike(String bikecode, String type, String dockName, int deposit, String licensePlate, int pin) {
		super(bikecode, type, dockName, deposit);
		this.licensePlate = licensePlate;
		this.pin = pin;
	}

	public ElectricBike(String bikecode, String type, String dockName, int deposit, int baseFee, int extraFee,
			String licensePlate, int pin) {
		super(bikecode, type, dockName, deposit, baseFee, extraFee);
		this.licensePlate = licensePlate;
		this.pin = pin;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public int calculateRentalFee(int minutes) {
		// TODO Auto-generated method stub
		return (int) (super.calculateRentalFee(minutes) * 1.5);
	}

	public static void main(String[] args) {
		Bike bike = new ElectricBike("0987654321", "type 1", "dock", 20, "abcyxz", 80);
		System.out.println(bike.toString());
		System.out.println(((ElectricBike) bike).getPin());
	}

}
