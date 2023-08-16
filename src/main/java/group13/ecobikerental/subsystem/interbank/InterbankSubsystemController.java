package group13.ecobikerental.subsystem.interbank;

import org.json.JSONObject;

import com.google.gson.Gson;

import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.exception.InternalServerErrorException;
import group13.ecobikerental.exception.InvalidCardException;
import group13.ecobikerental.exception.NotEnoughBalanceException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.utils.Configs;

public class InterbankSubsystemController {
	public InterbankBoundary interbankBoundary;

	public Transaction processPay(CreditCard card, int amount, String contents) {
		InterbankTransaction transaction = new InterbankTransaction(card.getCardCode(), card.getOwner(),
				card.getCvvCode(), card.getDateExpired(), "pay", contents, amount);
		// create request string
		JSONObject transactionJson = new JSONObject().put("cardCode", transaction.getCardCode())
				.put("owner", transaction.getOwner()).put("cvvCode", transaction.getCvvCode())
				.put("dateExpired", transaction.getDateExpired()).put("command", transaction.getCommand())
				.put("transactionContent", transaction.getTransactionContent()).put("amount", transaction.getAmount())
				.put("createdAt", transaction.getCreatedAt());
		
		JSONObject request = new JSONObject().put("transaction", transactionJson);
		request.put("version", "1.0.1");

		// send request
		String responseText = InterbankBoundary.query(Configs.PAY_TRANSACTION_URL, request.toString());
		System.out.println("Log:: responseText: " + responseText);
		
		Transaction trx = convertToTransaction(responseText);
		trx.setCard(card);
		return trx;
	}

	public Transaction processRefund(CreditCard card, int amount, String contents) {
		InterbankTransaction transaction = new InterbankTransaction(card.getCardCode(), card.getOwner(),
				card.getCvvCode(), card.getDateExpired(), "refund", contents, amount);
		// create request string
		JSONObject transactionJson = new JSONObject().put("cardCode", transaction.getCardCode())
				.put("owner", transaction.getOwner()).put("cvvCode", transaction.getCvvCode())
				.put("dateExpired", transaction.getDateExpired()).put("command", transaction.getCommand())
				.put("transactionContent", transaction.getTransactionContent()).put("amount", transaction.getAmount())
				.put("createdAt", transaction.getCreatedAt());

		JSONObject request = new JSONObject().put("transaction", transactionJson);
		request.put("version", "1.0.1");

		// send request
		String responseText = InterbankBoundary.query(Configs.REFUND_TRANSACTION_URL, request.toString());
		System.out.println("Log:: responseText: " + responseText);
		
		Transaction trx = convertToTransaction(responseText);
		trx.setCard(card);
		return trx;
	}

	private Transaction convertToTransaction(String responseText) {
		if (responseText == null) {
			return null;
		}
		JSONObject resJson = new JSONObject(responseText);
		Gson gson = new Gson();

		Transaction transactionRes = gson
				.fromJson(resJson.getJSONObject("transaction").toString(), Transaction.class);
		System.out.println(resJson.getString("errorCode"));
		
		transactionRes.setErrorCode(resJson.getString("errorCode"));

		switch (transactionRes.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
//		case "04":
//			throw new SuspiciousTransactionException();
//		case "05":
//			throw new NotEnoughTransactionInfoException();
//		case "06":
//			throw new InvalidVersionException();
//		case "07":
//			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return transactionRes;
	}
}
