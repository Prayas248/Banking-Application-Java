package service;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;
import model.Customer;
import model.CustomerService;
import model.PaymentType;

import java.util.Scanner;

public class TransferMoney {
    public TransferMoney() {

    }
    public static void transferMoneyService(Scanner sc,CustomerService cs){
        if (Main.currCustomer == null || Main.currAccount == null) {
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            System.out.println("Use Wallet Operations (option 6) for wallet transfers.");
            return;
        }
        else{
            System.out.println("Choose your account");
            try{
                Main.currAccount = Main.currCustomer.getAllAccounts();
            }
            catch (AccountNotFound e){
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Available customers:");
        cs.showCustomersExcept(Main.currCustomer.getCustomerId());
        System.out.println("Enter target customer ID: ");
        int targetId = sc.nextInt();
        Customer targetCustomer;
        try {
            targetCustomer = cs.getCustomers(targetId);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Choose Receiver's account");
        PaymentType targetPayment=null;
        try{
            targetPayment = targetCustomer.getAllAccounts();
        }
        catch (AccountNotFound e){
            System.out.println(e.getMessage());
            return;
        }




        System.out.println("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        try {
            ((BankAccount) Main.currAccount).transfer((BankAccount) targetPayment, amount);
        } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
