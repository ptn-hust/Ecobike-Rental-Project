package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;

/**
 * Base class for bike rental payment strategies.
 */
public abstract class Payment {
	/**
     * The bike associated with the payment.
     */
	protected Bike bike;

	/**
     * Constructs a Payment instance.
     */
	public Payment() {
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
