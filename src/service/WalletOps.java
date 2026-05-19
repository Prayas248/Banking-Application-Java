package service;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import exception.WalletLimitExceededException;
import main.Main;
import model.BankAccount;
import model.Customer;
import model.CustomerService;
import model.PaymentType;
import wallet.WalletOperations;

import java.util.Scanner;

public class WalletOps {
    public WalletOps() {

    }
    public static void WalletOpsService(Scanner sc,CustomerService cs){
        if (Main.currCustomer == null) {
            System.out.println("Select a customer first.");
            return;
        }
        // Auto-switch currAccount to the customer's wallet if it's not already a wallet
        if (!(Main.currAccount instanceof WalletOperations)) {
            PaymentType paytm = Main.currCustomer.getAccount("Paytm");
            PaymentType phonePe = Main.currCustomer.getAccount("PhonePe");
            if (paytm != null) {
                Main.currAccount = paytm;
            } else if (phonePe != null) {
                Main.currAccount = phonePe;
            } else {
                System.out.println("No wallet found. Open a wallet first (option 2).");
                return;
            }
        }
        WalletOperations wallet = (WalletOperations) Main.currAccount;
        System.out.println("1. Add Money");
        System.out.println("2. Pay Bill");
        System.out.println("3. Transfer to Wallet");
        int walletChoice = sc.nextInt();
        switch (walletChoice) {
            case 1: { // Add money to wallet
                System.out.println("Enter amount to add: ");
                double amount = sc.nextDouble();
                try {
                    wallet.addMoney(amount);
                } catch (WalletLimitExceededException | InvalidAmountException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2: { // Pay a bill from wallet
                System.out.println("Enter bill amount: ");
                double amount = sc.nextDouble();
                try {
                    wallet.payBill(amount);
                } catch (InsufficientBalanceException | InvalidAmountException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 3: { // Transfer from current wallet to another customer's wallet
                System.out.println("Available customers:");
                cs.showCustomersExcept(Main.currCustomer.getCustomerId());
                System.out.println("Enter target customer ID: ");
                int targetId = sc.nextInt();
                try {
                    Customer targetCustomer = cs.getCustomers(targetId);
                    System.out.println("Enter amount to transfer: ");
                    double amount = sc.nextDouble();
                    wallet.transferToWallet(targetCustomer, amount);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Customer not found.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
}
