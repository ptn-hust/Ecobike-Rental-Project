package group13.ecobikerental.DAL.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import group13.ecobikerental.DAL.DBConnector;
import group13.ecobikerental.entity.payment.transaction.Transaction;
//this class is copied from project Group 2
/**
 * Data Access Layer class for managing transaction-related database operations.
 */
public class TransactionDAL implements ITransactionDAL{
	/**
     * Saves a transaction to the database.
     * @param transaction The transaction to be saved.
     */
    public void save(Transaction transaction) {
        String sql = "insert into transaction values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = DBConnector.getInstance().getConnection().prepareStatement(sql);
            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setString(2, transaction.getCard().getCardCode());
            pstmt.setString(3, transaction.getCard().getOwner());
            pstmt.setString(4, transaction.getCard().getDateExpired());
            pstmt.setString(5, transaction.getCommand());
            pstmt.setString(6, transaction.getTransactionContent());
            pstmt.setString(7, String.valueOf(transaction.getAmount()));
            pstmt.setString(8, transaction.getCreatedAt());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
