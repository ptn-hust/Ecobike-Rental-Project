package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;
/**
 * Factory class for creating payment strategies based on the payment type.
 */
public class PaymentFactory {
	
	/**
     * Creates a payment strategy based on the specified type and bike.
     * @param type The payment type.
     * @param bike The bike associated with the payment.
     * @return An instance of a payment strategy.
     */
	public PaymentBase getPayment(String type, Bike bike) {
		if (type.equals("Normal")) {
			return new NormalPayment(bike);
		}
		return new PaymentBase();
	}
}
