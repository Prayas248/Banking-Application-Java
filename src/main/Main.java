package main;

import exception.*;
import model.*;
import wallet.PaytmWallet;
import wallet.PhonePeWallet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static Customer currCustomer;
    static PaymentType currAccount;

    public static void main(String[] args)
            throws InvalidPhoneNumberException, DuplicateCustomerException, InvalidEmailException,
            InsufficientBalanceException, InvalidAmountException, WalletLimitExceededException, AccountNotFound  {
        Scanner sc = new Scanner(System.in);
        Boolean ch = true;
        int customerCounter = 0;
        CustomerService cs = new CustomerService();

        while (ch){

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
                    "9. Exit\n");
            System.out.println("------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println("------------------------");
            switch (choice){
                case 1:
                {
                    System.out.println("Enter your name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter your email: ");
                    String email = sc.nextLine();
                    System.out.println("Enter your phone no: ");
                    String ph = sc.nextLine();

                    try{
                        cs.createUser(customerCounter++, name, email, ph);
                        currCustomer = new Customer(customerCounter, name, email, ph);
                    }
                    catch (InvalidEmailException | DuplicateCustomerException | InvalidPhoneNumberException e){
                        System.out.println(e);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                    break;
                }
                case 2:
                {
                    System.out.println("1. Savings account ");
                    System.out.println("2. Current account");
                    System.out.println("2. Create Wallet");

                    int bankChoice = sc.nextInt();


                    System.out.println("Enter your Account number: ");
                    sc.nextLine();
                    int accountId = sc.nextInt();
                    System.out.println("Enter your Balance: ");
                    int bal = sc.nextInt();

                    switch(bankChoice){
                        case 1:
                            try{
                                currAccount = new SavingsAccount(accountId,cs.getCustomers(),bal);
                                currCustomer.addAccount("Savings", currAccount);

                            }
                            catch(Exception e){
                                System.out.println("Entered wrong account details");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try{
                                currAccount = new CurrentAccount(accountId,cs.getCustomers(),bal);
                                currCustomer.addAccount("Current", currAccount);
                            }
                            catch(Exception e){
                                System.out.println("Entered wrong account details");
                            }
                            break;
                        case 3:
                            System.out.println("Enter choice for the wallet account: ");
                            System.out.println("1. Paytm, 2. Phone");
                            int walletInp = sc.nextInt();
                            switch(walletInp){
                                case(1):
                                    try{
                                        currAccount = new PaytmWallet(currCustomer);
                                        currCustomer.addAccount("Wallet", currAccount);
                                    }
                                    catch(Exception e){
                                        System.out.println("Entered wrong account details");
                                    }
                                    break;
                                case(2):
                                    try{
                                        currAccount = new PhonePeWallet(currCustomer);
                                        currCustomer.addAccount("Wallet", currAccount);
                                    }
                                    catch(Exception e){
                                        System.out.println("Entered wrong account details");
                                    }
                                    break;
                            }

                    }

                }
                case 3:
                    System.out.println("Enter");
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    cs.showCustomers();
                    System.out.println("Select id you want to switch back: ");
                    int switchID = sc.nextInt();
                    try{
                        currCustomer = cs.getCustomers(switchID);

                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("Entered customer ID is not found");
                    }

                    break;
                case 10:
                {
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
