package group13.ecobikerental.data_access_layer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import group13.ecobikerental.entity.payment.Transaction;

/**
 * This class controls the database transactions relate to Transaction
 */
public class TransactionDAL {
    /**
     * This method saves Transaction
     * @param transaction
     */
    public static void save(Transaction transaction) {
        String sql = "insert into transaction values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = DBConnector.getConnection().prepareStatement(sql);
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
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
