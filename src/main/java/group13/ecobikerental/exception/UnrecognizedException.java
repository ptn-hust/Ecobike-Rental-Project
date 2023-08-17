package group13.ecobikerental.exception;
/**
 * An exception that indicates an unrecognized or unexpected situation.
 * Subclass of RuntimeException.
 */
public class UnrecognizedException extends RuntimeException {
    public UnrecognizedException() {
        super("Unrecognized Exception");
    }
}
