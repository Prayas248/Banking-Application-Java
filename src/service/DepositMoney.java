package service;

import exception.AccountNotFound;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;

import java.util.Scanner;

public class DepositMoney {
    public DepositMoney() {

    }
    public static void depositMoneyService(Scanner sc){
        if (Main.currCustomer == null || Main.currAccount == null) {
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            System.out.println("Use Wallet Operations (option 6) to add money to a wallet.");
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
        System.out.println("Enter deposit amount: ");
        double amount = sc.nextDouble();
        try {
            ((BankAccount) Main.currAccount).deposit(amount);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }
}
