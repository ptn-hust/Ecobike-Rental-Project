package group13.ecobikerental.entity.payment;

import group13.ecobikerental.entity.bike.Bike;

/**
 * Represents a normal payment for bike rental.
 */
public class NormalPayment extends PaymentBase {
	/**
     * Constructs a NormalPayment instance for the given bike.
     * @param bike The bike for which the payment is made.
     */
	public NormalPayment(Bike bike) {
		super();
		super.bike = bike;
	}

	/**
     * Calculates the rental fee based on the specified number of minutes.
     * @param minutes The number of minutes for which the bike was rented.
     * @return The calculated rental fee.
     */
	@Override
	public int calculateRentalFee(int minutes) {
		return this.bike.calculateRentalFee(minutes);
	}
}
