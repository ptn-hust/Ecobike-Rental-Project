package group13.ecobikerental.exception;

/**
 * A base exception class for payment-related errors.
 * Subclass of RuntimeException.
 */
public class PaymentException extends RuntimeException {
	
    public PaymentException() {
    }
    
    public PaymentException(String message) {
    	super(message);
    }
}
