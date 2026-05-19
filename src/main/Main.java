package main;

import exception.*;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import wallet.PaytmWallet;
import wallet.PhonePeWallet;
import wallet.WalletOperations;

public class Main {
    // Currently active customer selected by the user
    static Customer currCustomer;
    // Currently active account (bank account or wallet) for the selected customer
    static PaymentType currAccount;

    public static void main(String[] args)
            throws InvalidPhoneNumberException, DuplicateCustomerException, InvalidEmailException,
            InsufficientBalanceException, InvalidAmountException, WalletLimitExceededException, AccountNotFound {
        Scanner sc = new Scanner(System.in);
        Boolean ch = true;
        int customerCounter = 0; // used as unique ID for each new customer
        CustomerService cs = new CustomerService();

        // Keep showing the menu until the user chooses to exit
        while (ch) {
            System.out.println();
            System.out.println("------------------------");
            System.out.println("WELCOME TO T&F INTERNATIONAL BANK!");
            System.out.println("------------------------");
            System.out.println("1. Create Customer\n" +
                    "2. Open Account\n" +
                    "3. Deposit\n" +
                    "4. Withdraw\n" +
                    "5. Transfer\n" +
                    "6. Wallet Operations\n" +
                    "7. Clone Account\n" +
                    "8. View Transactions\n" +
                    "9. Switch Customer\n" +
                    "10. Exit\n");
            System.out.println("------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println("------------------------");
            switch (choice) {
                case 1: { // Create a new customer
                    System.out.println("Enter your name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter your email: ");
                    String email = sc.nextLine();
                    System.out.println("Enter your phone no: ");
                    String ph = sc.nextLine();
                    try {
                        cs.createUser(customerCounter, name, email, ph);
                        // Set the newly created customer as the active customer
                        currCustomer = cs.getCustomers();
                        customerCounter++;
                    } catch (InvalidEmailException | DuplicateCustomerException | InvalidPhoneNumberException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: { // Open a bank account or wallet for the current customer
                    if (currCustomer == null) {
                        System.out.println("Create a customer first.");
                        break;
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
                                currAccount = new SavingsAccount(accountId, currCustomer, bal);
                                currCustomer.addAccount("Savings", currAccount);
                                System.out.println("Savings account created. Account #" + accountId + " | Balance: ₹" + bal);
                            } catch (Exception e) {
                                System.out.println("Entered wrong account details");
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                currAccount = new CurrentAccount(accountId, currCustomer, bal);
                                currCustomer.addAccount("Current", currAccount);
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
                                currAccount = new PaytmWallet(currCustomer);
                                currCustomer.addAccount("Paytm", currAccount);
                            } catch (Exception e) {
                                System.out.println("Entered wrong account details");
                            }
                        } else if (walletInp == 2) {
                            try {
                                currAccount = new PhonePeWallet(currCustomer);
                                currCustomer.addAccount("PhonePe", currAccount);
                                System.out.println("PhonePe wallet created successfully.");
                            } catch (Exception e) {
                                System.out.println("Entered wrong account details");
                            }
                        }
                    }
                    break;
                }
                case 3: { // Deposit money into the current bank account
                    if (currCustomer == null || currAccount == null) {
                        System.out.println("Select a customer and account first.");
                        break;
                    }
                    if (!(currAccount instanceof BankAccount)) {
                        System.out.println("Use Wallet Operations (option 6) to add money to a wallet.");
                        break;
                    }
                    else{
                        System.out.println("Choose your account");
                        currAccount = currCustomer.getAllAccounts();

                    }
                    System.out.println("Enter deposit amount: ");
                    double amount = sc.nextDouble();
                    try {
                        ((BankAccount) currAccount).deposit(amount);
                    } catch (InvalidAmountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: { // Withdraw money from the current bank account
                    if (currCustomer == null || currAccount == null) {
                        System.out.println("Select a customer and account first.");
                        break;
                    }
                    if (!(currAccount instanceof BankAccount)) {
                        System.out.println("Use Wallet Operations (option 6) to pay bills from a wallet.");
                        break;
                    }
                    else{
                        System.out.println("Choose your account");
                        currAccount = currCustomer.getAllAccounts();
                    }
                    System.out.println("Enter withdrawal amount: ");
                    double amount = sc.nextDouble();
                    try {
                        ((BankAccount) currAccount).withdraw(amount);
                    } catch (InvalidAmountException | InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: { // Transfer money from the current account to another customer's account
                    if (currCustomer == null || currAccount == null) {
                        System.out.println("Select a customer and account first.");
                        break;
                    }
                    if (!(currAccount instanceof BankAccount)) {
                        System.out.println("Use Wallet Operations (option 6) for wallet transfers.");
                        break;
                    }
                    else{
                        System.out.println("Choose your account");
                        currAccount = currCustomer.getAllAccounts();
                    }
                    System.out.println("Available customers:");
                    cs.showCustomersExcept(currCustomer.getCustomerId());
                    System.out.println("Enter target customer ID: ");
                    int targetId = sc.nextInt();
                    Customer targetCustomer;
                    try {
                        targetCustomer = cs.getCustomers(targetId);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    System.out.println("Choose Receiver's account");
                    PaymentType  targetPayment = targetCustomer.getAllAccounts();


                    System.out.println("Enter amount to transfer: ");
                    double amount = sc.nextDouble();
                    try {
                        ((BankAccount) currAccount).transfer((BankAccount) targetPayment, amount);
                    } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: { // Wallet operations: add money, pay bill, or transfer to another wallet
                    if (currCustomer == null) {
                        System.out.println("Select a customer first.");
                        break;
                    }
                    // Auto-switch currAccount to the customer's wallet if it's not already a wallet
                    if (!(currAccount instanceof WalletOperations)) {
                        PaymentType paytm = currCustomer.getAccount("Paytm");
                        PaymentType phonePe = currCustomer.getAccount("PhonePe");
                        if (paytm != null) {
                            currAccount = paytm;
                        } else if (phonePe != null) {
                            currAccount = phonePe;
                        } else {
                            System.out.println("No wallet found. Open a wallet first (option 2).");
                            break;
                        }
                    }
                    WalletOperations wallet = (WalletOperations) currAccount;
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
                            cs.showCustomersExcept(currCustomer.getCustomerId());
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
                    break;
                }
                case 7: {
                    System.out.println("No reason to clone.");
                }
                case 8: { // View all transactions of the current customer
                    if (currCustomer == null) {
                        System.out.println("Select a customer first.");
                        break;
                    }
                    ArrayList<Transaction> transactions = currCustomer.getAllTransactions();
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        for (Transaction t : transactions) {
                            System.out.println(t);
                        }
                    }
                    break;
                }
                case 9: { // Switch the active customer and load their first account
                    cs.showCustomers();
                    System.out.println("Select customer ID to switch to: ");
                    int switchID = sc.nextInt();
                    try {
                        currCustomer = cs.getCustomers(switchID);
                        currAccount = currCustomer.getFirstAccount();
                        System.out.println("Switched to: " + currCustomer.getCustomerName());
                        if (currAccount == null) {
                            System.out.println("No account found. Open an account using option 2.");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Customer not found.");
                    }
                    break;
                }
                case 10: { // Exit the application
                    System.out.println();
                    System.out.println("------------------------");
                    System.out.println("Thankyou for Banking with us!");
                    System.out.println("------------------------");
                    System.out.println();
                    ch = false;
                    break;
                }
            }
        }
    }
}
