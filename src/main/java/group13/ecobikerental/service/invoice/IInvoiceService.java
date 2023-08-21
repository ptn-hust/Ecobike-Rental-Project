package group13.ecobikerental.service.invoice;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.transaction.Transaction;

public interface IInvoiceService {
	int calculateRentalFee(String timeRental, String paymentMethod, Bike bike);

	void save(Transaction transaction);

	void save(Invoice invoice);
}
