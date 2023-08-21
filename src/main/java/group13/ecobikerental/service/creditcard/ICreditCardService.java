package group13.ecobikerental.service.creditcard;

public interface ICreditCardService {
	boolean validateCreditCardInfo(String expDate, String cvvCode, String owner, String cardCode);
}
