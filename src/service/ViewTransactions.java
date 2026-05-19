package service;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewTransactions {
    public ViewTransactions() {

    }
    public static void ViewTransactionService(){
        if (Main.currCustomer == null) {
            System.out.println("Select a customer first.");
            return;
        }
        ArrayList<Transaction> transactions = Main.currCustomer.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}
