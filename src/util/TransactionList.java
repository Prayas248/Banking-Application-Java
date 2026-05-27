package util;

import model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

// Global store for all transactions across all customers, keyed by transaction ID
public class TransactionList{

    private static final Logger logger = LoggerFactory.getLogger(TransactionList.class);

    private static HashMap<Integer, Transaction> transactionlist = new HashMap<>();

    // Utility class — not meant to be instantiated
    private TransactionList(){}

    // Add a new transaction to the global store
    public static void addTransaction(Transaction transaction){
        Integer id = transaction.getTransactionId();
        transactionlist.put(id, transaction);
        logger.debug("Added transaction with id = {} to the central transaction list", id);
    }

    // Retrieve a transaction by its ID
    public static Transaction getTransaction(Integer id){
        logger.debug("Retrieving transaction with id = {} from the central transaction list", id);
        return transactionlist.get(id);
    }
}
