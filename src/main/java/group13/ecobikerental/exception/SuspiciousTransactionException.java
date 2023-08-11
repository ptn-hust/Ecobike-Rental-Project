package group13.ecobikerental.exception;

public class SuspiciousTransactionException extends PaymentException {
    public SuspiciousTransactionException() {
        super("ERROR: Suspicious Transaction Report!");
    }
}
