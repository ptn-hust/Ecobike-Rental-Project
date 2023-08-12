package group13.ecobikerental.data_access_layer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import group13.ecobikerental.data_access_layer.DBConnector;
import group13.ecobikerental.entity.invoice.Invoice;

/**
 * This class controls the database transactions relate to Invoice
 */
public class InvoiceDAL {
    /**
     * This method saves invoice
     */
    public static void save(){
        String sql = "insert into invoice(iv_time_rental,iv_rental_fees,bike,iv_pay_deposit_trx,iv_refund_trx)  values(?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setString(1, Invoice.getInstance().getRentalTime());
            pstmt.setString(2, String.valueOf(Invoice.getInstance().getRentalFee()));
            pstmt.setString(3, String.valueOf(Invoice.getInstance().getBike().getBikeId()));
            pstmt.setString(4, Invoice.getInstance().getPayDepositTransaction().getTransactionId());
            pstmt.setString(5, Invoice.getInstance().getRefundTransaction().getTransactionId());
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
