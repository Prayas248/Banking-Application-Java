package main;

import exception.*;
import java.util.Scanner;
import model.*;
import service.*;

public class Main {
    // Currently active customer selected by the user
    public static Customer currCustomer;
    // Currently active account (bank account or wallet) for the selected customer
    public static PaymentType currAccount;

    public static void main(String[] args)
            throws InvalidPhoneNumberException, DuplicateCustomerException, InvalidEmailException,
            InsufficientBalanceException, InvalidAmountException, WalletLimitExceededException, AccountNotFound {
        Scanner sc = new Scanner(System.in);
        Boolean ch = true;
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
                case 1: { // Create a new customer using Service of Create Customer
                    CreateCustomer.creationOfCustomerService(sc,cs);
                    break;
                }
                case 2: { // Open a bank account or wallet for the current customer using Service of Create Account
                    CreateAccount.createAccountService(sc);
                    break;
                }
                case 3: { // Deposit money into the current bank account using DepositMoney service
                    DepositMoney.depositMoneyService(sc);
                    break;
                }
                case 4: { // Withdraw money from the current bank account using TransferMoney service
                    WithdrawMoney.withdrawMoneyService(sc);
                    break;
                }
                case 5: { // Transfer money from the current account to another customer's account using TransferMoney Service
                    TransferMoney.transferMoneyService(sc,cs);
                    break;
                }
                case 6: { // Wallet operations: add money, pay bill, or transfer to another wallet
                    WalletOps.WalletOpsService(sc,cs);
                    break;
                }
                case 7: {
                    System.out.println("No reason to clone.");
                    break;
                }
                case 8: { // View all transactions of the current customer
                    ViewTransactions.ViewTransactionService();
                    break;
                }
                case 9: { // Switch the active customer and load their first account
                    SwitchCustomer.switchCustomerService(sc,cs);
                    break;
                }
                case 10: { // Exit the application
                    System.out.println();
                    System.out.println("------------------------");
                    System.out.println("Thank you for Banking with us!");
                    System.out.println("------------------------");
                    System.out.println();
                    ch = false;
                    break;
                }
            }
        }
    }
}
