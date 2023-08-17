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
 * Controller class responsible for handling the return bike process.
 */
public class ReturnBikeController extends BaseController {

	private InterbankInterface interbank;

	/**
     * Calculate the rental fee based on the rental time and bike type.
     * @param timeRental The rental time.
     * @param bike       The Bike object.
     * @return The calculated rental fee.
     */
	public int calculateRentalFee(String timeRental, Bike bike) {
		int amount = InvoiceBL.getInstance().calculateRentalFee(timeRental, "Normal", bike);
		return amount;
	}

	/**
     * Calculate the refund amount based on the deposit and rental fee.
     * @param deposit    The deposit amount.
     * @param rentalFee  The rental fee amount.
     * @return The calculated refund amount.
     */
	private int calculateRefund(int deposit, int rentalFee) {
		return (deposit - rentalFee);
	}

	/**
     * Handle the process of returning a bike to a dock.
     * @param dockName    The name of the dock.
     * @param rentalFees  The rental fees.
     * @param bike        The Bike object.
     * @return A map containing the result and message of the return process.
     * @throws SQLException if a database access error occurs.
     */
	public Map<String, String> returnBike(String dockName, int rentalFees, Bike bike) throws SQLException {
		Map<String, String> result = new Hashtable<String, String>();

		if (new DockDAL().checkDockAvailable(dockName)) {

			int refundAmount = calculateRefund(Invoice.getInstance().getBike().getDeposit(), rentalFees);

			interbank = new InterbankSubsystem();
			try {
				Transaction refundTransaction = interbank.refund(Invoice.getInstance().getPayTransaction().getCard(),
						refundAmount, "request refund");
				result.put("RESULT", "PAYMENT SUCCESSFUL!");
				result.put("MESSAGE", "You have successfully refund!");
				// update invoice
				Invoice.getInstance().setRefundTransaction(refundTransaction);
				Invoice.getInstance().setRentalFee(rentalFees);
				// update bike
				new BikeDAL().updateBike(Invoice.getInstance().getBike().getBikeCode(), dockName, 0);
				TransactionDAL.save(refundTransaction);
				InvoiceDAL.save();
			} catch (PaymentException | UnrecognizedException ex) {
				result.put("RESULT", "PAYMENT FAILED!");
				result.put("MESSAGE", ex.getMessage());
			}
		} else {
			result.put("RESULT", "DOCK IS NOT AVAILABLE");
			result.put("MESSAGE", "Please choose another dock again!!!");
		}

		return result;
	}
}
