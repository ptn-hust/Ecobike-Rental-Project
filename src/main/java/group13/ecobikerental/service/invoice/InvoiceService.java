package group13.ecobikerental.service.invoice;

import group13.ecobikerental.DAL.invoice.InvoiceDAL;
import group13.ecobikerental.DAL.transaction.TransactionDAL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.Payment;
import group13.ecobikerental.entity.payment.PaymentFactory;
import group13.ecobikerental.entity.payment.transaction.Transaction;

/**
 * Business Logic class to calculate rental fees for bikes.
 */
public class InvoiceService implements IInvoiceService{
	private InvoiceDAL invoiceDAL;
	private TransactionDAL transactionDAL;
	
	public InvoiceService() {
		this.invoiceDAL = new InvoiceDAL();
		this.transactionDAL = new TransactionDAL();
	};

	/**
	 * Calculate the rental fee for a bike based on the rental time and payment
	 * method.
	 * 
	 * @param timeRental    The rental time in HH:mm:ss format.
	 * @param paymentMethod The payment method chosen by the user.
	 * @param bike          The bike for which the rental fee is calculated.
	 * @return The calculated rental fee.
	 */
	public int calculateRentalFee(String timeRental, String paymemtMethod, Bike bike) {
		int rentalFee = 0;
		int minutes = timeToMinute(timeRental);
		PaymentFactory paymentFactory = new PaymentFactory();
		Payment payment = paymentFactory.getPayment(paymemtMethod, bike);
		rentalFee = payment.calculateRentalFee(minutes);
		return rentalFee;
	}

	/**
	 * Convert rental time from HH:mm:ss format to total minutes.
	 * 
	 * @param timeRental The rental time in HH:mm:ss format.
	 * @return The total minutes.
	 */
	private int timeToMinute(String timeRental) {
		String[] timeArr = timeRental.split(":");
		int minutes = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
		if (Integer.parseInt(timeArr[2]) > 0) {
			minutes++;
		}
		return minutes;
	}
	
	public void save(Transaction transaction) {
		this.transactionDAL.save(transaction);
	}
	public void save(Invoice invoice) {
		this.invoiceDAL.save(invoice);
	}
	
}
