package group13.ecobikerental.subsystem.interbank;

import group13.ecobikerental.utils.Utils;

/**
 * Represents a transaction for communication with the interbank system.
 */
public class InterbankTransaction {
	private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private String command;
    private String transactionContent;
    private String transactionId;
    private String createdAt;
    private int amount;

    /**
     * Constructs a new InterbankTransaction.
     * @param cardCode           The card code of the credit card.
     * @param owner              The owner of the credit card.
     * @param cvvCode            The CVV code of the credit card.
     * @param dateExpired        The expiration date of the credit card.
     * @param command            The command for the transaction (e.g., "pay" or "refund").
     * @param transactionContent The content of the transaction.
     * @param amount             The amount of the transaction.
     */
    public InterbankTransaction(String cardCode, String owner, String cvvCode, String dateExpired, String commmand,
                       String transactionContent, int amount) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
        this.command = commmand;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.transactionId = null;
        this.createdAt = Utils.getToday();
    }

    /**
     * Gets the card code of the credit card.
     * @return The card code.
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * Sets the card code of the credit card.
     * @param cardCode The card code to set.
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * Gets the owner of the credit card.
     * @return The owner of card.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the credit card.
     * @param owner The owner of card.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the CVV code of the credit card.
     * @return The CVV code.
     */
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * Sets the CVV code of the credit card.
     * @param cvvCode The CVV code to set.
     */
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     * Gets the expiration date of the credit card.
     * @return The expiration date.
     */
    public String getDateExpired() {
        return dateExpired;
    }

    /**
     * Sets the expiration date of the credit card.
     * @param dateExpired The expiration date to set.
     */
    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    /**
     * Gets the command for the transaction.
     * @return The command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command for the transaction.
     * @param command The command to set.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Gets the content of the transaction.
     * @return The transaction content.
     */
    public String getTransactionContent() {
        return transactionContent;
    }

    /**
     * Sets the content of the transaction.
     * @param transactionContent The transaction content to set.
     */
    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    /**
     * Gets the amount of the transaction.
     * @return The transaction amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     * @param amount The transaction amount to set.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the creation date of the transaction.
     * @return The creation date.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation date of the transaction.
     * @param createdAt The creation date to set.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the transaction ID.
     * @return The transaction ID.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     * @param transactionId The transaction ID to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
