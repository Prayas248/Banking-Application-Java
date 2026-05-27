package service;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import exception.WalletLimitExceededException;
import main.Main;
import model.Customer;
import model.CustomerService;
import model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wallet.WalletOperations;

import java.util.Scanner;

public class WalletOps {

    private static final Logger logger = LoggerFactory.getLogger(WalletOps.class);

    public WalletOps() {

    }
    public static void WalletOpsService(Scanner sc,CustomerService cs){
        logger.info("WalletOpsService has started");

        if (Main.currCustomer == null) {
            logger.warn("Current account is null");
            System.out.println("Select a customer first.");
            return;
        }
        // Auto-switch currAccount to the customer's wallet if it's not already a wallet
        if (!(Main.currAccount instanceof WalletOperations)) {
            logger.debug("Switching to WalletOperations for customerId = {}", Main.currCustomer.getCustomerId());
            PaymentType paytm = Main.currCustomer.getAccount("Paytm");
            PaymentType phonePe = Main.currCustomer.getAccount("PhonePe");
            if (paytm != null) {
                logger.warn("Paytm selected for customerId = {}", Main.currCustomer.getCustomerId());
                Main.currAccount = paytm;
            } else if (phonePe != null) {
                logger.warn("PhonePe selected for customerId = {}", Main.currCustomer.getCustomerId());
                Main.currAccount = phonePe;
            } else {
                logger.warn("No Paytm or PhonePe found for customerId = {}", Main.currCustomer.getCustomerId());
                System.out.println("No wallet found. Open a wallet first (option 2).");
                return;
            }
        }

        WalletOperations wallet = (WalletOperations) Main.currAccount;
        System.out.println("1. Add Money");
        System.out.println("2. Pay Bill");
        System.out.println("3. Transfer to Wallet");
        int walletChoice = sc.nextInt();

        logger.info("Choice selected = {} by customerId = {}", walletChoice, Main.currCustomer.getCustomerId());
        switch (walletChoice) {
            case 1: { // Add money to wallet
                System.out.println("Enter amount to add: ");
                double amount = sc.nextDouble();
                logger.debug("Amount entered to add: {} for customerId = {}", amount,Main.currCustomer.getCustomerId());
                try {
                    wallet.addMoney(amount);
                    logger.info("Amount has been successfully added to wallet");
                } catch (WalletLimitExceededException | InvalidAmountException e) {
                    logger.error("Wallet Limit Exceeded / Invalid amount entered");
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2: { // Pay a bill from wallet
                System.out.println("Enter bill amount: ");
                double amount = sc.nextDouble();
                logger.debug("Amount entered to withdraw: {} for customerId = {}", amount,Main.currCustomer.getCustomerId());

                try {
                    wallet.payBill(amount);
                    logger.info("Amount has been successfully withdrawn from wallet");
                } catch (InsufficientBalanceException | InvalidAmountException e) {
                    logger.error("Insufficient Balance / Invalid amount entered");
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 3: { // Transfer from current wallet to another customer's wallet
                logger.info("Transfer from one wallet to another");
                System.out.println("Available customers:");
                cs.showCustomersExcept(Main.currCustomer.getCustomerId());
                System.out.println("Enter target customer ID: ");
                int targetId = sc.nextInt();

                logger.debug("Transfer from one wallet to another for receiver with customerId = {}", targetId);
                try {
                    Customer targetCustomer = cs.getCustomers(targetId);
                    System.out.println("Enter amount to transfer: ");
                    double amount = sc.nextDouble();
                    logger.debug("Amount entered to transfer: {} from customerId = {} to receiver with cutomerId = {}", amount,Main.currCustomer.getCustomerId() ,targetCustomer.getCustomerId());
                    wallet.transferToWallet(targetCustomer, amount);
                    logger.info("Amount has been successfully transferred to wallet");
                } catch (IndexOutOfBoundsException e) {
                    logger.error("No customers found");
                    System.out.println("Customer not found.");
                } catch (Exception e) {
                    logger.error("Unexpected error while transferring to wallet");
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
}
