package group13.ecobikerental.subsystem.interbank;

import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.PaymentTransaction;
import group13.ecobikerental.exception.PaymentException;

public class Response {
    private String errorCode;
    private Transaction transaction;

    public PaymentTransaction toPaymentTransaction() {
        if (this.errorCode.equals("00")) {
            CreditCard creditCard = new CreditCard(this.transaction.getCardCode(), this.transaction.getOwner(),
                this.transaction.getCvvCode(), this.transaction.getDateExpired());
            return new PaymentTransaction(this.errorCode, creditCard, this.transaction.getTransactionId(),
                this.transaction.getTransactionContent(), this.transaction.getCommand(), this.transaction.getAmount(),
                this.transaction.getCreatedAt());
        } else {
            return new PaymentTransaction(this.errorCode, null, null, null, null, -1, null);
        }

    }
}
