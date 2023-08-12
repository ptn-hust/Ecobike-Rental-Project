package group13.ecobikerental.business_layer;

import group13.ecobikerental.entity.bike.Bike;

public class NormalPayment extends PaymentBase {
	/**
	 * @param bike
	 */
	public NormalPayment(Bike bike) {
		super();
		super.bike = bike;
	}

	@Override
	public int calculateRentalFee(int minutes) {
		return this.bike.calculateRentalFee(minutes);
	}
}
