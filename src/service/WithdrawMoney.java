package service;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class WithdrawMoney {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawMoney.class);

    public WithdrawMoney() {

    }
    public static void withdrawMoneyService(Scanner sc){
        logger.info("Withdrawing Money Service Started");

        if (Main.currCustomer == null || Main.currAccount == null) {
            logger.warn("Customer or Account not found");
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            logger.warn("Account type not valid");
            System.out.println("Use Wallet Operations (option 6) to pay bills from a wallet.");
            return;
        }
        else{
            System.out.println("Choose your account");
            try{
                Main.currAccount = Main.currCustomer.getAllAccounts();
                logger.info("Current account using for withdraw is {}", Main.currAccount);
            }
            catch (AccountNotFound e){
                logger.error("No account found to deposit");
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        logger.debug("Withdrawn amount is {} for accountId = {} | customerId = {}", amount,((BankAccount) Main.currAccount).getAccountNumber() ,Main.currCustomer.getCustomerId());
        try {
            ((BankAccount) Main.currAccount).withdraw(amount);
            logger.info("Withdrawn successful for accountId = {} | customerId = {}", ((BankAccount) Main.currAccount).getAccountNumber(),Main.currCustomer.getCustomerId());

        } catch (InvalidAmountException | InsufficientBalanceException e) {
            logger.error("Invalid deposit amount");
            System.out.println(e.getMessage());
        }
    }
}
