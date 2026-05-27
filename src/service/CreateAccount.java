package service;

import main.Main;
import model.CurrentAccount;
import model.SavingsAccount;
import wallet.PaytmWallet;
import wallet.PhonePeWallet;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAccount {

    private static final Logger logger = LoggerFactory.getLogger(CreateAccount.class);

    public CreateAccount() {

    }
    public static void createAccountService(Scanner sc){
        logger.info("Creation of account started");
        if (Main.currCustomer == null) {
            logger.warn("Current Customer is null");
            System.out.println("Create a customer first.");
            return;
        }
        System.out.println("1. Savings account");
        System.out.println("2. Current account");
        System.out.println("3. Create Wallet");
        int bankChoice = sc.nextInt();

        logger.debug("User selected account type option={}", bankChoice);

        if (bankChoice == 1 || bankChoice == 2) {
            System.out.println("Enter your Account number: ");
            sc.nextLine();
            int accountId = sc.nextInt();
            System.out.println("Enter your Balance: ");
            double bal = sc.nextDouble();

            logger.debug("Received account balance of accountId={}  |  customerId = {} ", accountId,Main.currCustomer.getCustomerId());

            if (bankChoice == 1) {
                try {

                    Main.currAccount = new SavingsAccount(accountId, Main.currCustomer, bal);
                    Main.currCustomer.addAccount("Savings", Main.currAccount);

                    logger.info("Savings account created successfully for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("Savings account created. Account #" + accountId + " | Balance: ₹" + bal);
                } catch (Exception e) {
                    logger.error("Failed to create a saving account for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());
                    System.out.println("Entered wrong account details");
                    e.printStackTrace();
                }
            } else {
                try {
                    Main.currAccount = new CurrentAccount(accountId, Main.currCustomer, bal);
                    Main.currCustomer.addAccount("Current", Main.currAccount);

                    logger.info("Current account created successfully for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("Current account created. Account #" + accountId + " | Balance: ₹" + bal);
                } catch (Exception e) {
                    logger.error("Failed to create a current account for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("Entered wrong account details");
                }
            }
        } else if (bankChoice == 3) {
            // Wallet creation: link wallet to the current customer
            System.out.println("Enter choice for the wallet account: ");
            System.out.println("1. Paytm, 2. PhonePe");

            logger.info("Wallet type selected");

            int walletInp = sc.nextInt();
            if (walletInp == 1) {
                try {
                    Main.currAccount = new PaytmWallet(Main.currCustomer);
                    Main.currCustomer.addAccount("Paytm", Main.currAccount);
                    logger.info("Paytm wallet created successfully for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                } catch (Exception e) {
                    logger.error("Failed to create a Paytm wallet for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("Entered wrong account details");
                }
            } else if (walletInp == 2) {
                try {
                    Main.currAccount = new PhonePeWallet(Main.currCustomer);
                    Main.currCustomer.addAccount("PhonePe", Main.currAccount);
                    logger.info("PhonePe wallet created successfully for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("PhonePe wallet created successfully.");
                } catch (Exception e) {
                    logger.error("Failed to create a PhonePe wallet for accountId={}  |  customerId ={}", accountId, Main.currCustomer.getCustomerId());

                    System.out.println("Entered wrong account details");
                }
            }
        }
    }
}
