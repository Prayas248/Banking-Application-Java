package util;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionList{
    private static HashMap<Integer, Transaction> transactionlist = new HashMap<>();
    public TransactionList(){

    }
    public static void addTransaction(Transaction transaction){
        Integer id =  transaction.getTransactionId();
        transactionlist.put(id, transaction);
    }
    public static Transaction getTransaction(Integer id){
        return transactionlist.get(id);
    }
}
