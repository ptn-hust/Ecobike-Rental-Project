package group13.ecobikerental.exception;

/**
 * Represents an exception indicating that one or more required fields are missing.
 * Subclass of EcobikeSystemException.
 */
public class MissingFieldException extends EcobikeSystemException {
	public MissingFieldException() {
		super("ERROR: All field must be required");
	}
	
	/**
     * Constructs a new MissingFieldException with a custom error message.
     * @param message The error message.
     */
	public MissingFieldException(String message) {
		super(message);
	}

}
