package util;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionList{
    private static HashMap<Integer, ArrayList<Transaction>> transactionlist = new HashMap<>();
    public TransactionList(){

    }
    public static void addTransaction(Transaction transaction){
        Integer id =  transaction.getTransactionId();
        ArrayList<Transaction> list = (ArrayList<Transaction>) transactionlist.get(id).clone();
        list.add(transaction);
        transactionlist.put(id, list);
    }
    public static ArrayList<Transaction> getTransaction(Integer id){
        return transactionlist.get(id);
    }
}
