package service;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewTransactions {

    private static final Logger logger = LoggerFactory.getLogger(ViewTransactions.class);

    public ViewTransactions() {

    }
    public static void ViewTransactionService(){

        logger.info("ViewTransactionService has started");

        if (Main.currCustomer == null) {
            logger.warn("Current account is null");
            System.out.println("Select a customer first.");
            return;
        }
        logger.debug("Account found for customerId = {}",Main.currCustomer.getCustomerId());
        ArrayList<Transaction> transactions = Main.currCustomer.getAllTransactions();
        if (transactions.isEmpty()) {
            logger.warn("No transactions found for customerId = {}", Main.currCustomer.getCustomerId());
            System.out.println("No transactions found.");
        } else {
            logger.info("Transactions found for customerId = {}", Main.currCustomer.getCustomerId());
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}
