package service;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;

import java.util.Scanner;

public class WithdrawMoney {
    public WithdrawMoney() {

    }
    public static void withdrawMoneyService(Scanner sc){
        if (Main.currCustomer == null || Main.currAccount == null) {
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            System.out.println("Use Wallet Operations (option 6) to pay bills from a wallet.");
            return;
        }
        else{
            System.out.println("Choose your account");
            Main.currAccount = Main.currCustomer.getAllAccounts();
        }
        System.out.println("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        try {
            ((BankAccount) Main.currAccount).withdraw(amount);
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
