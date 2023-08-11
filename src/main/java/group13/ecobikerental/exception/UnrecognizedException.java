package group13.ecobikerental.exception;

public class UnrecognizedException extends RuntimeException {
    public UnrecognizedException() {
        super("ERROR: Something went wrowng!");
    }
}
