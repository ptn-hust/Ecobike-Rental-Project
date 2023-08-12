package group13.ecobikerental.controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import group13.ecobikerental.business_layer.CreditCardBL;
import group13.ecobikerental.data_access_layer.BikeDAL;
import group13.ecobikerental.data_access_layer.TransactionDAL;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.entity.payment.Transaction;
import group13.ecobikerental.exception.PaymentException;
import group13.ecobikerental.exception.UnrecognizedException;
import group13.ecobikerental.subsystem.InterbankInterface;
import group13.ecobikerental.subsystem.InterbankSubsystem;

public class PayDepositController extends BaseController {
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
     * @throws Exception 
     */
    public Map<String, String> payDepositRequest(CreditCard card, int amount, String contents) throws Exception {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
//            card.setDateExpired(CreditCardBL.getExpirationDate(card.getDateExpired()));
//            System.out.println("date expired ne: " + card.getDateExpired());
            this.interbank = new InterbankSubsystem();
            
            System.out.println("init interbankSystem instance");           
          
            Transaction transaction = interbank.pay(card, amount, contents);
            
            System.out.println("12h : " + transaction.toString());
            
            // add depositTransaction to Invoice instance
            Invoice.getInstance().setPayDepositTransaction(transaction);
            
            // update status of bike in DB
            new BikeDAL().updateBike(Invoice.getInstance().getBike().getBikeCode(),null, 1);
            
            // save transaction to DB
            TransactionDAL.save(transaction);
            
            // set return result
            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the bike of deposit!");
        } catch (PaymentException | UnrecognizedException | SQLException ex) {
            result.put("MESSAGE", ex.getMessage());
        }

        return result;
    }
}
