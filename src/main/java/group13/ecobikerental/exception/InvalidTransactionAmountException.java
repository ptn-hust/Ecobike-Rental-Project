package group13.ecobikerental.exception;

public class InvalidTransactionAmountException extends PaymentException {
    public InvalidTransactionAmountException() {
        super("ERROR: Invalid Transaction Amount!");
    }
}

