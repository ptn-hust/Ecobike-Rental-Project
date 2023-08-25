package group13.ecobikerental.DAL.transaction;

import group13.ecobikerental.entity.payment.transaction.Transaction;

/**
 * Interface for managing transaction-related database operations.
 */
public interface ITransactionDAL {

    /**
     * Save a transaction to the database.
     * 
     * @param transaction The Transaction object to be saved.
     */
    void save(Transaction transaction);
}
