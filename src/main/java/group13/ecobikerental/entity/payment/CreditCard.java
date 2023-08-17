package group13.ecobikerental.entity.payment;

/**
 * Entity class for credit card used for payment.
 */
public class CreditCard {
    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    
    /**
     * Constructor to create a CreditCard object.
     * @param cardCode    The code of the credit card.
     * @param owner       The owner's name of the credit card.
     * @param cvvCode     The CVV code of the credit card.
     * @param dateExpired The expiration date of the credit card.
     */
    public CreditCard(String cardCode, String owner, String cvvCode, String dateExpired) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    /**
     * Get the card code of the credit card.
     * @return The card code.
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * Set the card code of the credit card.
     * @param cardCode The card code to set.
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * Get the owner's name of the credit card.
     * @return The owner's name.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner's name of the credit card.
     * @param owner The owner's name to set.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Get the CVV code of the credit card.
     * @return The CVV code.
     */
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * Set the CVV code of the credit card.
     * @param cvvCode The CVV code to set.
     */
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     * Get the expiration date of the credit card.
     * @return The expiration date.
     */
    public String getDateExpired() {
        return dateExpired;
    }

    /**
     * Set the expiration date of the credit card.
     * @param dateExpired The expiration date to set.
     */
    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }
}
