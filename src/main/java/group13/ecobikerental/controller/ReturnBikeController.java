package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.transaction.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.service.bike.IBikeService;
import group13.ecobikerental.service.dock.IDockService;
import group13.ecobikerental.service.invoice.IInvoiceService;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

/**
 * Controller class responsible for handling the return bike process.
 */
public class ReturnBikeController extends BaseController {
	private IDockService dockServiceInstance;
	private IInvoiceService invoiceServiceInstance;
	private InterbankInterface interbank;
	private IBikeService bikeServiceInstance;

	public ReturnBikeController(IBikeService bikeServiceInstance, IInvoiceService invoiceServiceInstance,
			IDockService dockServiceInstance) throws SQLException {
		this.dockServiceInstance = dockServiceInstance;
		this.invoiceServiceInstance = invoiceServiceInstance;
		this.bikeServiceInstance = bikeServiceInstance;
	}

	public List<Dock> getDockList() {
		return dockServiceInstance.getDockList();
	}

	/**
	 * Calculate the rental fee based on the rental time and bike type.
	 * 
	 * @param timeRental The rental time.
	 * @param bike       The Bike object.
	 * @return The calculated rental fee.
	 */
	public int calculateRentalFee(String timeRental, Bike bike) {
		int amount = invoiceServiceInstance.calculateRentalFee(timeRental, Invoice.getInstance().getPaymentMethod(),
				bike);
		return amount;
	}

	/**
	 * Calculate the refund amount based on the deposit and rental fee.
	 * 
	 * @param deposit   The deposit amount.
	 * @param rentalFee The rental fee amount.
	 * @return The calculated refund amount.
	 */
	private int calculateRefund(int deposit, int rentalFee) {
		return (deposit - rentalFee);
	}

	/**
	 * Handle the process of returning a bike to a dock.
	 * 
	 * @param dockName   The name of the dock.
	 * @param rentalFees The rental fees.
	 * @param bike       The Bike object.
	 * @return A map containing the result and message of the return process.
	 * @throws SQLException if a database access error occurs.
	 */
	public Map<String, String> returnBike(String dockName, int rentalFees, Bike bike) throws SQLException {
		Map<String, String> result = new Hashtable<String, String>();
//		this.dockServiceInstance = new DockService();

		if (this.dockServiceInstance.checkDockAvailable(dockName)) {

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
				this.bikeServiceInstance.updateBike(Invoice.getInstance().getBike().getBikeCode(), dockName, 0);
				this.invoiceServiceInstance.save(refundTransaction);
				this.invoiceServiceInstance.save(Invoice.getInstance());
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
