package group13.ecobikerental.subsystem;

import group13.ecobikerental.entity.creditcard.CreditCard;
import group13.ecobikerental.entity.payment.transaction.Transaction;
import group13.ecobikerental.subsystem.interbank.InterbankSubsystemController;

/**
 * Represents the subsystem responsible for interacting with the interbank system to process payments and refunds.
 */
public class InterbankSubsystem implements InterbankInterface {

    private InterbankSubsystemController controller;

    /**
     * Initializes a new instance of the InterbankSubsystem.
     */
    public InterbankSubsystem() {
        this.controller = new InterbankSubsystemController();
    }

    /**
     * Processes a payment using the provided credit card information.
     * @param card     The credit card information for the payment.
     * @param amount   The amount of money to be paid.
     * @param contents Additional contents or description of the payment.
     * @return A Transaction object representing the payment transaction.
     */
    public Transaction pay(CreditCard card, int amount, String contents) {
        Transaction transaction = controller.processPay(card, amount, contents);
        return transaction;
    }

    /**
     * Processes a refund using the provided credit card information.
     * @param card     The credit card information for the refund.
     * @param amount   The amount of money to be refunded.
     * @param contents Additional contents or description of the refund.
     * @return A Transaction object representing the refund transaction.
     */
    public Transaction refund(CreditCard card, int amount, String contents) {
        Transaction transaction = controller.processRefund(card, amount, contents);
        return transaction;
    }
}
