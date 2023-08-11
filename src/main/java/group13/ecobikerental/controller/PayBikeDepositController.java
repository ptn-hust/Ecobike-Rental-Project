package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.business_layer.CreditCardBL;
import group13.ecobikerental.dbconnnection_layer.BikeDL;
import group13.ecobikerental.dbconnnection_layer.PaymentTransactionDL;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.PaymentTransaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

public class PayBikeDepositController extends BaseController {
    private InterbankInterface interbank;

    public boolean checkCardInfo(String expDate, String cvvCode) {
        return CreditCardBL.validateCreditCardInfo(expDate, cvvCode);
    }

    /**
     * @param card     -
     * @param amount   -
     * @param contents -
     *
     * @return
     */
    public Map<String, String> payDeposit(CreditCard card, int amount, String contents) {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            card.setDateExpired(CreditCardBL.getExpirationDate(card.getDateExpired()));
            this.interbank = new InterbankSubsystem();

            PaymentTransaction transaction = interbank.payDeposit(card, amount, contents);
            Invoice.getInstance().setPayDepositTransaction(transaction);
            new BikeDL().updateBike(Invoice.getInstance().getBike().getBikeCode(),null, 1);
            PaymentTransactionDL.save(transaction);
            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the bike of deposit!");
        } catch (PaymentException | UnrecognizedException | SQLException ex) {
            result.put("MESSAGE", ex.getMessage());
        }

        return result;
    }
}
