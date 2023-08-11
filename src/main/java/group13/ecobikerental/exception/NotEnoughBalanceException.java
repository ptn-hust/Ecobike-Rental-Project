package group13.ecobikerental.exception;

public class NotEnoughBalanceException extends PaymentException{

    public NotEnoughBalanceException() {
        super("ERROR: Not enough balance in card!");
    }

}
