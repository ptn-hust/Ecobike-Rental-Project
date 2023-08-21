package group13.ecobikerental.exception;
/**
 * An exception that indicates an unrecognized or unexpected situation.
 * Subclass of RuntimeException.
 */
public class UnrecognizedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnrecognizedException() {
        super("Unrecognized Exception");
    }
}
