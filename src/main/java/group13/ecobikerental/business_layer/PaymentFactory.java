package group13.ecobikerental.business_layer;

import group13.ecobikerental.entity.bike.Bike;

public class PaymentFactory {
	public PaymentBase getPayment(String type, Bike bike) {
		if (type.equals("Normal")) {
			return new NormalPayment(bike);
		}
		return new PaymentBase();
	}
}
