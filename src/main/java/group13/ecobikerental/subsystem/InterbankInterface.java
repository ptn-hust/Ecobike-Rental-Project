package group13.ecobikerental.subsystem;

import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;

public interface InterbankInterface {
    Transaction pay(CreditCard card, int amount, String contents) throws Exception;

    Transaction refund(CreditCard card, int amount, String contents);
}
