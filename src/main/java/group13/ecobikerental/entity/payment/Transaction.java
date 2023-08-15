package group13.ecobikerental.entity.payment;

public class Transaction {

	private String errorCode;

	private CreditCard card;

	private String command;

	private String transactionId;

	private String transactionContent;

	private int amount;

	private String createdAt;

	public Transaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
			String command, int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.command = command;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
		return;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
