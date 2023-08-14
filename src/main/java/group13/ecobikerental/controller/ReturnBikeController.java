package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.business_layer.InvoiceBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.data_access_layer.InvoiceDAL;
import group13.ecobikerental.data_access_layer.TransactionDAL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

/**
 * This class controls the flow of events when users return bike to dock
 */
public class ReturnBikeController extends BaseController {

	private InterbankInterface interbank;

	public int calculateRentalFee(String timeRental, Bike bike) {
		int amount = InvoiceBL.getInstance().calculateRentalFee(timeRental, "Normal", bike);
		return amount;
	}

	public Map<String, String> returnBike(String dockName, String timeRental, Bike bike) throws SQLException {
		int rentalFees = calculateRentalFee(timeRental, bike);

		Map<String, String> result = new Hashtable<String, String>();

		if (new DockDAL().checkDockAvailable(dockName)) {
			result.put("RESULT", "PAYMENT FAILED!");
			int refundAmount = Invoice.getInstance().getBike().getDeposit() - rentalFees;
			interbank = new InterbankSubsystem();
			try {
				Transaction refundTransaction = interbank.refund(
						Invoice.getInstance().getPayTransaction().getCard(), refundAmount, "request refund");
				result.put("RESULT", "PAYMENT SUCCESSFUL!");
				result.put("MESSAGE", "You have successfully refund!");

				Invoice.getInstance().setRefundTransaction(refundTransaction);
				Invoice.getInstance().setRentalFee(rentalFees);
				new BikeDAL().updateBike(Invoice.getInstance().getBike().getBikeCode(), dockName, 0);
				TransactionDAL.save(refundTransaction);
				InvoiceDAL.save();
			} catch (PaymentException | UnrecognizedException ex) {
				result.put("MESSAGE", ex.getMessage());
			}
		} else {
			result.put("RESULT", "DOCK IS NOT AVAILABLE");
			result.put("MESSAGE", "Please choose another dock again!!!");
		}

		return result;
	}
}
