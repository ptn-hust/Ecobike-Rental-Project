package group13.ecobikerental.entity.payment.transaction;

import group13.ecobikerental.entity.creditcard.CreditCard;

/**
 * Represents a transaction made using a credit card.
 */
public class Transaction {

	private String errorCode;

	private CreditCard card;

	private String command;

	private String transactionId;

	private String transactionContent;

	private int amount;

	private String createdAt;

	/**
     * Constructor to create a Transaction instance.
     * @param errorCode         The error code associated with the transaction.
     * @param card              The credit card used for the transaction.
     * @param transactionId     The ID of the transaction.
     * @param transactionContent The content of the transaction.
     * @param command           The command associated with the transaction.
     * @param amount            The amount of the transaction.
     * @param createdAt         The timestamp when the transaction was created.
     */
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

	/**
     * Get the error code associated with the transaction.
     * @return The error code.
     */
	public String getErrorCode() {
		return errorCode;
	}

	/**
     * Set the error code associated with the transaction.
     * @param errorCode The error code.
     */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
     * Get the credit card used for the transaction.
     * @return The credit card.
     */
	public CreditCard getCard() {
		return card;
	}

	/**
     * Set the credit card used for the transaction.
     * @param card The credit card.
     */
	public void setCard(CreditCard card) {
		this.card = card;
		return;
	}

	/**
     * Get the ID of the transaction.
     * @return The transaction ID.
     */
	public String getTransactionId() {
		return transactionId;
	}

	/**
     * Get the content of transaction
     * @return The content of transaction
     */
	public String getTransactionContent() {
		return transactionContent;
	}

	/**
     * Set the content of transaction
     * @param transactionContent Content of transaction
     */
	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	/**
     * Get the amount of the transaction.
     * @return The amount of the transaction.
     */
	public int getAmount() {
		return amount;
	}

	/**
     * Set the amount of the transaction.
     * @param amount The amount of the transaction.
     */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
     * Get the timestamp when the transaction was created.
     * @return The timestamp when the transaction was created.
     */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
     * Set the timestamp when the transaction was created.
     * @param createdAt The timestamp when the transaction was created.
     */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
     * Get the command associated with the transaction.
     * @return The command associated with the transaction.
     */
	public String getCommand() {
		return command;
	}

	/**
     * Set the command associated with the transaction.
     * @param command The command associated with the transaction.
     */
	public void setCommand(String command) {
		this.command = command;
	}
}
