package group13.ecobikerental.DAL.invoice;

import group13.ecobikerental.entity.invoice.Invoice;

/**
 * Interface for managing invoice-related database operations.
 */
public interface IInvoiceDAL {

    /**
     * Save an Invoice object to the database.
     * 
     * @param invoice The Invoice object to be saved.
     */
    void save(Invoice invoice);
}
