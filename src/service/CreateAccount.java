package service;

import main.Main;
import model.CurrentAccount;
import model.SavingsAccount;
import wallet.PaytmWallet;
import wallet.PhonePeWallet;

import java.util.Scanner;

public class CreateAccount {
    public CreateAccount() {

    }
    public static void createAccountService(Scanner sc){
        if (Main.currCustomer == null) {
            System.out.println("Create a customer first.");
            return;
        }
        System.out.println("1. Savings account");
        System.out.println("2. Current account");
        System.out.println("3. Create Wallet");
        int bankChoice = sc.nextInt();

        if (bankChoice == 1 || bankChoice == 2) {
            System.out.println("Enter your Account number: ");
            sc.nextLine();
            int accountId = sc.nextInt();
            System.out.println("Enter your Balance: ");
            double bal = sc.nextDouble();

            if (bankChoice == 1) {
                try {
                    Main.currAccount = new SavingsAccount(accountId, Main.currCustomer, bal);
                    Main.currCustomer.addAccount("Savings", Main.currAccount);
                    System.out.println("Savings account created. Account #" + accountId + " | Balance: ₹" + bal);
                } catch (Exception e) {
                    System.out.println("Entered wrong account details");
                    e.printStackTrace();
                }
            } else {
                try {
                    Main.currAccount = new CurrentAccount(accountId, Main.currCustomer, bal);
                    Main.currCustomer.addAccount("Current", Main.currAccount);
                    System.out.println("Current account created. Account #" + accountId + " | Balance: ₹" + bal);
                } catch (Exception e) {
                    System.out.println("Entered wrong account details");
                }
            }
        } else if (bankChoice == 3) {
            // Wallet creation: link wallet to the current customer
            System.out.println("Enter choice for the wallet account: ");
            System.out.println("1. Paytm, 2. PhonePe");
            int walletInp = sc.nextInt();
            if (walletInp == 1) {
                try {
                    Main.currAccount = new PaytmWallet(Main.currCustomer);
                    Main.currCustomer.addAccount("Paytm", Main.currAccount);
                } catch (Exception e) {
                    System.out.println("Entered wrong account details");
                }
            } else if (walletInp == 2) {
                try {
                    Main.currAccount = new PhonePeWallet(Main.currCustomer);
                    Main.currCustomer.addAccount("PhonePe", Main.currAccount);
                    System.out.println("PhonePe wallet created successfully.");
                } catch (Exception e) {
                    System.out.println("Entered wrong account details");
                }
            }
        }
    }
}
