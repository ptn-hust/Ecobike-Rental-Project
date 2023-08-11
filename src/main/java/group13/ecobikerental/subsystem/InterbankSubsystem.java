package group13.ecobikerental.subsystem;

import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.PaymentTransaction;
import group13.ecobikerental.subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {

    /**
     * Represent the controller of the subsystem
     */
    private InterbankSubsystemController ctrl;

    /**
     * Initializes a newly created {@code InterbankSubsystem} object so that it
     * represents an Interbank subsystem.
     */
    public InterbankSubsystem() {
        String str = new String();
        this.ctrl = new InterbankSubsystemController();
    }

    /**
     * @see InterbankInterface#payDeposit(group13.ecobikerental.entity.payment.CreditCard, int,
     * java.lang.String)
     */
    public PaymentTransaction payDeposit(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.payDeposit(card, amount, contents);
        return transaction;
    }

    /**
     * this method process refund.
     *
     * @param card
     * @param amount
     * @param contents
     *
     * @return PaymentTransaction
     *
     * @see InterbankInterface#refund(group13.ecobikerental.entity.payment.CreditCard, int,
     * java.lang.String)
     */
    public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.refund(card, amount, contents);
        return transaction;
    }
}
