package service;

import exception.AccountNotFound;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class DepositMoney {

    private static Logger logger = LoggerFactory.getLogger(DepositMoney.class);

    public DepositMoney() {

    }
    public static void depositMoneyService(Scanner sc){

        logger.info("Deposit Money Service invoked");

        if (Main.currCustomer == null || Main.currAccount == null) {
            logger.warn("Customer or Account not found");
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            logger.warn("Account type not valid");
            System.out.println("Use Wallet Operations (option 6) to add money to a wallet.");
            return;
        }
        else{
            System.out.println("Choose your account");
            try{
                Main.currAccount = Main.currCustomer.getAllAccounts();
                logger.info("Current account using for deposit is {}", Main.currAccount);
            }
            catch (AccountNotFound e){
                logger.error("No account found to deposit");
                System.out.println(e.getMessage());
                return;
            }


        }
        System.out.println("Enter deposit amount: ");
        double amount = sc.nextDouble();

        logger.debug("Deposit amount is {} for accountId = {} | customerId = {}", amount,((BankAccount) Main.currAccount).getAccountNumber() ,Main.currCustomer.getCustomerId());
        try {
            ((BankAccount) Main.currAccount).deposit(amount);
            logger.info("Deposit successful for accountId = {} | customerId = {}", ((BankAccount) Main.currAccount).getAccountNumber(),Main.currCustomer.getCustomerId());
        } catch (InvalidAmountException e) {
            logger.error("Invalid deposit amount");
            System.out.println(e.getMessage());
        }
    }
}
