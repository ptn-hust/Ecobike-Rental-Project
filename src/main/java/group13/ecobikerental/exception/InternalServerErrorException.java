package group13.ecobikerental.exception;

/**
 * Represents an exception indicating an internal server error.
 * Subclass of PaymentException.
 */
public class InternalServerErrorException extends PaymentException {
    public InternalServerErrorException() {
        super("ERROR: Internal Server Error!");
    }
}
