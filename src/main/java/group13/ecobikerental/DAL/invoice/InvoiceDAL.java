package group13.ecobikerental.DAL.invoice;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import group13.ecobikerental.DAL.DBConnector;
import group13.ecobikerental.entity.invoice.Invoice;

/**
 * Data Access Layer class for managing invoice-related database operations.
 */
public class InvoiceDAL implements IInvoiceDAL{
	
	public void save(Invoice invoice) {
		String sql = "insert into invoice(iv_time_rental,iv_rental_fees,bike,iv_pay_deposit_trx,iv_refund_trx)  values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = DBConnector.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, invoice.getRentalTime());
			pstmt.setString(2, String.valueOf(invoice.getRentalFee()));
			pstmt.setString(3, String.valueOf(invoice.getBike().getBikeId()));
			pstmt.setString(4, invoice.getPayTransaction().getTransactionId());
			pstmt.setString(5, invoice.getRefundTransaction().getTransactionId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
