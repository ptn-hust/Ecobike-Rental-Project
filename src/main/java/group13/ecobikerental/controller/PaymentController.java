package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.business_layer.CreditCardBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.TransactionDAL;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

public class PaymentController extends BaseController {
	private InterbankInterface interbank;

	public boolean checkCardInfo(String expDate, String cvvCode) {
		return CreditCardBL.getInstance().validateCreditCardInfo(expDate, cvvCode);
	}

	public Map<String, String> payDepositRequest(CreditCard card, int amount, String contents) throws Exception {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.interbank = new InterbankSubsystem();

			Transaction transaction = interbank.pay(card, amount, contents);

			// add depositTransaction to Invoice instance
			Invoice.getInstance().setPayTransaction(transaction);

			// update status of bike in DB
			new BikeDAL().updateBike(Invoice.getInstance().getBike().getBikeCode(), null, 1);

			// save transaction to DB
			TransactionDAL.save(transaction);

			// set return result
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the bike of deposit!");
		} catch (PaymentException | UnrecognizedException | SQLException ex) {
			result.put("MESSAGE", ex.getMessage());
		}

		return result;
	}
}
