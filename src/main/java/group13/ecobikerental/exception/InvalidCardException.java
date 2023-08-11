package group13.ecobikerental.exception;

public class InvalidCardException extends PaymentException {
    public InvalidCardException() {
        super("ERROR: Invalid card!");
    }
}
