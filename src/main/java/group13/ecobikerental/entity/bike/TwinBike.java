package group13.ecobikerental.entity.bike;

public class TwinBike extends Bike {
	@Override
	public int calculateRentalFee(int minutes) {
		// TODO Auto-generated method stub
		return (int) ((super.calculateRentalFee(minutes)) * 1.5);
	}
}
