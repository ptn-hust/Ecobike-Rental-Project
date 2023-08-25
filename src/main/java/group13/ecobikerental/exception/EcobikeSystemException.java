package group13.ecobikerental.exception;

/**
 * Represents a custom exception specific to the EcoBikeRental system.
 */
public class EcobikeSystemException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EcobikeSystemException() {

	}

    /**
     * Constructs a new EcobikeSystemException with the specified detail message.
     * @param message The detail message.
     */
	public EcobikeSystemException(String message) {
		super(message);
	}
}