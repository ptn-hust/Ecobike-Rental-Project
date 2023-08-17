package group13.ecobikerental.exception;

/**
 * Represents an exception indicating that the provided card information is invalid.
 * Subclass of PaymentException.
 */
public class InvalidCardException extends PaymentException {
    public InvalidCardException() {
        super("ERROR: Invalid card information!");
    }
}
