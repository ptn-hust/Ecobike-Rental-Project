package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;

/**
 * Base class for bike rental payment strategies.
 */
public class PaymentBase {
	/**
     * The bike associated with the payment.
     */
	protected Bike bike;

	/**
     * Constructs a PaymentBase instance.
     */
	public PaymentBase() {
	};

	/**
     * Calculates the rental fee based on the specified number of minutes.
     * @param minutes The number of minutes for which the bike was rented.
     * @return The calculated rental fee.
     */
	public int calculateRentalFee(int minutes) {
		return 0;
	};
}
