package group13.ecobikerental.business_layer;

import java.util.Calendar;

import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.exception.InvalidCardException;

public class CreditCardBL {
	private static CreditCardBL instance;

	public static CreditCardBL getInstance() {
		if (instance == null) {
			instance = new CreditCardBL();
		}
		return instance;
	}

	public boolean validateCreditCardInfo(final String expDate, final String cvvCode) {
		if (isValidCvvCode(cvvCode) || isValidExpiryDate(expDate)) {
			return true;
		}

		return false;
	}

	public boolean isValidExpiryDate(String input) {
		if (input == null || !input.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
			return false; // Invalid format
		}

		int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100; // Get last two digits of current year
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // Months are 0-based

		String[] parts = input.split("/");
		int expiryMonth = Integer.parseInt(parts[0]);
		int expiryYear = Integer.parseInt(parts[1]);

		if (expiryYear < 0 || expiryYear > 99) {
			return false; // Invalid year range
		}

		if (expiryYear < currentYear || (expiryYear == currentYear && expiryMonth < currentMonth)) {
			return false; // Expired
		}

		return true; // Valid expiration date
	}

	private boolean isValidCvvCode(final String cvvCode) {
		if (cvvCode == null || cvvCode.length() != 3 || !cvvCode.matches("^[0-9]{3}$")) {
			return false;
		}
		return true;
	}
}
