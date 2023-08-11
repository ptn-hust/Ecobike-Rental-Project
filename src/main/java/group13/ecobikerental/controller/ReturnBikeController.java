package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.business_layer.InvoiceBL;
import group13.ecobikerental.dbconnnection_layer.BikeDL;
import group13.ecobikerental.dbconnnection_layer.DockDL;
import group13.ecobikerental.dbconnnection_layer.InvoiceDL;
import group13.ecobikerental.dbconnnection_layer.PaymentTransactionDL;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.PaymentTransaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

/**
 * This class controls the flow of events when users return bike to dock
 */
public class ReturnBikeController extends BaseController {
    /**
     * Represent the Interbank subsystem
     */
    private InterbankInterface interbank;
    /**
     * The method calculates the rental fee
     * @param timeRental - the time that user rented
     * @return rentalFee - the fee corresponds to the rental time
     */
    public int calculateRentalFee(String timeRental) {
        return InvoiceBL.calculateRentalFee(timeRental);
    }

    /**
     * This method takes responsibility for processing the return bike
     * @param dockName - the name of dock where user returns bike
     * @param timeRental - the time that user rented bike
     * @return result
     * @throws SQLException - Exceptions relate to SQL
     */
    public Map<String, String> returnBike(String dockName, String timeRental) throws SQLException {
        int rentalFees = calculateRentalFee(timeRental);
        Map<String, String> result = new Hashtable<String, String>();
        if (DockDL.getInstance().checkDockAvailable(dockName)) {
            result.put("RESULT", "PAYMENT FAILED!");
            int refundAmount = Invoice.getInstance().getBike().getDeposit() - rentalFees;
            interbank = new InterbankSubsystem();
            try {
                PaymentTransaction refundTransaction =
                    interbank.refund(Invoice.getInstance().getPayDepositTransaction().getCard(), refundAmount,
                        "request refund");
                result.put("RESULT", "PAYMENT SUCCESSFUL!");
                result.put("MESSAGE", "You have successfully refund!");

                Invoice.getInstance().setRefundTransaction(refundTransaction);
                Invoice.getInstance().setRentalFee(rentalFees);
                new BikeDL().updateBike(Invoice.getInstance().getBike().getBikeCode(), dockName, 0);
                PaymentTransactionDL.save(refundTransaction);
                InvoiceDL.save();
            } catch (PaymentException | UnrecognizedException ex) {
                result.put("MESSAGE", ex.getMessage());
            }
        } else {
            result.put("RESULT", "NOT AVAILABLE");
            result.put("MESSAGE", "Please choose another dock again!!!");
        }
        return result;
    }
}
