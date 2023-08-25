package group13.ecobikerental.exception;

/**
 * A base exception class for payment-related errors.
 * Subclass of RuntimeException.
 */
public class PaymentException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentException() {
    }
    
    public PaymentException(String message) {
    	super(message);
    }
}
