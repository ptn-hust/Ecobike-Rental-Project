package group13.ecobikerental.exception;

/**
 * An exception indicating that a payment or transaction cannot be completed due to insufficient balance.
 * Subclass of PaymentException.
 */
public class NotEnoughBalanceException extends PaymentException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughBalanceException() {
        super("ERROR: Not enough balance");
    }

}
