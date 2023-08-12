package group13.ecobikerental.entity.invoice;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;

/**
 * Entity Invoice
 */
public class Invoice {
    /**
     * Represent for bike
     */
    private Bike bike;
    private String rentalTime;
    private int rentalFee;
    /**
     * Represent for payment transaction.
     */
    private Transaction payDepositTransaction;
    private Transaction refundTransaction;

//    private static Invoice instance = new Invoice();
    private static Invoice instance;

    private Invoice() {
    }


    /**
     * This method saves the invoice.
     */
    public void save() {

    }


    public static Invoice getInstance() {
    	if(instance == null) {
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

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Transaction getPayDepositTransaction() {
        return payDepositTransaction;
    }

    public void setPayDepositTransaction(Transaction payDepositTransaction) {
        this.payDepositTransaction = payDepositTransaction;
    }

    public Transaction getRefundTransaction() {
        return refundTransaction;
    }

    public void setRefundTransaction(Transaction refundTransaction) {
        this.refundTransaction = refundTransaction;
    }

    public void setRentalTime(String rentalTime) {
        this.rentalTime = rentalTime;
    }

    public String getRentalTime() {
        return rentalTime;
    }

    public int getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(int rentalFee) {
        this.rentalFee = rentalFee;
    }
}
