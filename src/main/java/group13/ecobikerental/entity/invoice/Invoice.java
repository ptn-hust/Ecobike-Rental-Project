package group13.ecobikerental.entity.invoice;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.payment.transaction.Transaction;

/**
 * Entity Invoice
 */
public class Invoice {
	/**
	 * Represent for bike
	 */
	private Bike bike;

	private String rentalTime; 	// rental duration
	
	private int rentalFee;
	/**
	 * Represent for payment transaction.
	 */
	private Transaction payTransaction;
	private Transaction refundTransaction;
	private String paymentMethod;

	private static Invoice instance;

	public static Invoice getInstance() {
		if (instance == null) {
			instance = new Invoice();
		}
		return instance;
	}

	public static void setInstance(Invoice instance) {
		Invoice.instance = instance;
	}

	public static void setInstance() {
		instance = null;
	}

	/**
	 * @return the bike
	 */
	public Bike getBike() {
		return bike;
	}

	/**
	 * @param bike the bike to set
	 */
	public void setBike(Bike bike) {
		this.bike = bike;
	}

	/**
	 * @return the rentalTime
	 */
	public String getRentalTime() {
		return rentalTime;
	}

	/**
	 * @param rentalTime the rentalTime to set
	 */
	public void setRentalTime(String rentalTime) {
		this.rentalTime = rentalTime;
	}

	/**
	 * @return the rentalFee
	 */
	public int getRentalFee() {
		return rentalFee;
	}

	/**
	 * @param rentalFee the rentalFee to set
	 */
	public void setRentalFee(int rentalFee) {
		this.rentalFee = rentalFee;
	}

	/**
	 * @return the payTransaction
	 */
	public Transaction getPayTransaction() {
		return payTransaction;
	}

	/**
	 * @param payTransaction the payTransaction to set
	 */
	public void setPayTransaction(Transaction payTransaction) {
		this.payTransaction = payTransaction;
	}

	/**
	 * @return the refundTransaction
	 */
	public Transaction getRefundTransaction() {
		return refundTransaction;
	}

	/**
	 * @param refundTransaction the refundTransaction to set
	 */
	public void setRefundTransaction(Transaction refundTransaction) {
		this.refundTransaction = refundTransaction;
	}
	
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
