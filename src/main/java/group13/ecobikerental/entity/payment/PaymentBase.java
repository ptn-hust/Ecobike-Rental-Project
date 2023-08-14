package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;

public class PaymentBase {
	protected Bike bike;

	public PaymentBase() {
	};

	public int calculateRentalFee(int minutes) {
		return 0;
	};
}
