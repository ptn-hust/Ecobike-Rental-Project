package group13.ecobikerental.subsystem;

import group13.ecobikerental.entity.creditcard.CreditCard;
import group13.ecobikerental.entity.payment.transaction.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;

public interface InterbankInterface {
	
	/**
     * Processes a payment using the provided credit card information.
     * @param card     The credit card information for the payment.
     * @param amount   The amount of money to be paid.
     * @param contents Additional contents or description of the payment.
     * @return A Transaction object representing the payment transaction.
     * @throws PaymentException     If a payment error occurs.
     * @throws UnrecognizedException If an unrecognized or unexpected error occurs.
     */
    Transaction pay(CreditCard card, int amount, String contents) throws Exception;

    
    /**
     * Processes a refund using the provided credit card information.
     * @param card     The credit card information for the refund.
     * @param amount   The amount of money to be refunded.
     * @param contents Additional contents or description of the refund.
     * @return A Transaction object representing the refund transaction.
     * @throws PaymentException     If a refund error occurs.
     * @throws UnrecognizedException If an unrecognized or unexpected error occurs.
     */
    Transaction refund(CreditCard card, int amount, String contents);
}
