package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.entity.creditcard.CreditCard;

//import group13.ecobikerental.DAL.BikeDAL;
//import group13.ecobikerental.DAL.TransactionDAL;

import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.transaction.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.service.bike.BikeService;
import group13.ecobikerental.service.bike.IBikeService;
import group13.ecobikerental.service.creditcard.CreditCardService;
import group13.ecobikerental.service.creditcard.ICreditCardService;
import group13.ecobikerental.service.invoice.IInvoiceService;
import group13.ecobikerental.service.invoice.InvoiceService;
import group13.ecobikerental.subsystem.InterbankInterface;

/**
 * Controller responsible for handling payment-related actions.
 */
public class PaymentController extends BaseController {
	private InterbankInterface interbank;
	private ICreditCardService creditCardServiceInstance;
	private IInvoiceService invoiceServiceInstance;
	private IBikeService bikeServiceInstance;

	public PaymentController(InterbankInterface interbank, CreditCardService creditCardServiceInstance,
			InvoiceService invoiceServiceInstance, BikeService bikeServiceInstance) throws SQLException {
		this.creditCardServiceInstance = creditCardServiceInstance;
		this.bikeServiceInstance =bikeServiceInstance;
		this.invoiceServiceInstance = invoiceServiceInstance;
		this.interbank = interbank;
	}

	/**
	 * Checks if the provided credit card information is valid.
	 * 
	 * @param expDate The expiration date of the credit card.
	 * @param cvvCode The CVV code of the credit card.
	 * @return true if the credit card information is valid, otherwise false.
	 */
//	public boolean checkCardInfoRequest(String expDate, String cvvCode) {
//		return creditCardServiceInstance.validateCreditCardInfo(expDate, cvvCode);
//	}
	public boolean checkCardInfoRequest(CreditCard card) {
		return creditCardServiceInstance.validateCreditCardInfo(card.getDateExpired(), card.getCvvCode(),
				card.getOwner(), card.getCardCode());
	}

	/**
	 * Initiates a payment request for bike deposit using the provided credit card.
	 * 
	 * @param card     The credit card used for payment.
	 * @param amount   The amount to be paid.
	 * @param contents Additional payment information.
	 * @return A map containing the result and message of the payment request.
	 * @throws Exception if there is an error during payment processing.
	 */
	public Map<String, String> payDepositRequest(CreditCard card, int amount, String contents) throws Exception {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
//			this.interbank = new InterbankSubsystem();
//			this.invoiceServiceInstance = new InvoiceService();

			Transaction transaction = interbank.pay(card, amount, contents);

			// add depositTransaction to Invoice instance
			Invoice.getInstance().setPayTransaction(transaction);

			// update status of bike in DB
			this.bikeServiceInstance.updateBike(Invoice.getInstance().getBike().getBikeCode(), null, 1);

			// save transaction to DB
			this.invoiceServiceInstance.save(transaction);

			// save invoice to DB

			// set return result
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the bike of deposit!");
		} catch (PaymentException | UnrecognizedException | SQLException ex) {
			result.put("MESSAGE", ex.getMessage());
		}

		return result;
	}
}
