package group13.ecobikerental.exception;

/**
 * An exception indicating that a payment or transaction cannot be completed due to insufficient balance.
 * Subclass of PaymentException.
 */
public class NotEnoughBalanceException extends PaymentException{

    public NotEnoughBalanceException() {
        super("ERROR: Not enough balance");
    }

}
