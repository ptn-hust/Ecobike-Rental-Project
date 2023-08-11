package group13.ecobikerental.exception;

public class InvalidVersionException extends PaymentException{
    public InvalidVersionException() {
        super("ERROR: Invalid Version Information!");
    }
}
