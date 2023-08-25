package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;
/**
 * Factory class for creating payment strategies based on the payment type.
 */
public class PaymentFactory {
	public Payment getPayment(String type, Bike bike) {
		if (type.equals("Normal")) {
			return new NormalPayment(bike);
		} else if(type.equals("Day")) {
			return new DayPayment();
		}
		return null;
	}
}
