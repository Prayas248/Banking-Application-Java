package main;

import exception.*;
import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidPhoneNumberException, DuplicateCustomerException, InvalidEmailException {
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

                    switch(bankChoice){
                        case 1:
                            System.out.println("Enter your Account number: ");
                            sc.nextLine();
                            int accountId = sc.nextLine();
                            System.out.println("Enter your Balance: ");
                            int bal = sc.nextLine();

                            try{
                                BankAccount currSav = new SavingsAccount(accountId,cs.getCustomers(customerCounter),bal);

                            }
                            catch()



                    }

                }
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
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
