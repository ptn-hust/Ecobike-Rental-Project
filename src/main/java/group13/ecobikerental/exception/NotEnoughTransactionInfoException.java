package group13.ecobikerental.exception;

public class NotEnoughTransactionInfoException extends PaymentException {
    public NotEnoughTransactionInfoException() {
        super("ERROR: Not Enough Transcation Information");
    }
}
