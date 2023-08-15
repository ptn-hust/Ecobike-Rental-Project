package group13.ecobikerental.subsystem.interbank;

import group13.ecobikerental.utils.Utils;

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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}