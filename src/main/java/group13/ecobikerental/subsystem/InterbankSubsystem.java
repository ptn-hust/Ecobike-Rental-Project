package group13.ecobikerental.subsystem;

import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {

    private InterbankSubsystemController controller;

    public InterbankSubsystem() {
        this.controller = new InterbankSubsystemController();
    }

    public Transaction pay(CreditCard card, int amount, String contents) {
        Transaction transaction = controller.pay(card, amount, contents);
        return transaction;
    }

    public Transaction refund(CreditCard card, int amount, String contents) {
        Transaction transaction = controller.refund(card, amount, contents);
        return transaction;
    }
}
