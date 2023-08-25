package group13.ecobikerental.exception;

/**
 * Represents an exception indicating an internal server error.
 * Subclass of PaymentException.
 */
public class InternalServerErrorException extends PaymentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
        super("ERROR: Internal Server Error!");
    }
}
