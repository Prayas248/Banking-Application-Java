package util;

import model.Transaction;

import java.util.HashMap;

// Global store for all transactions across all customers, keyed by transaction ID
public class TransactionList{
    private static HashMap<Integer, Transaction> transactionlist = new HashMap<>();

    // Utility class — not meant to be instantiated
    private TransactionList(){}

    // Add a new transaction to the global store
    public static void addTransaction(Transaction transaction){
        Integer id = transaction.getTransactionId();
        transactionlist.put(id, transaction);
    }

    // Retrieve a transaction by its ID
    public static Transaction getTransaction(Integer id){
        return transactionlist.get(id);
    }
}
