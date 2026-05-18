package model;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public abstract class BankAccount {
    private String accountNumber;
    private Customer customer;
    private double balance;

    public BankAccount(String accountNumber, Customer customer, double balance){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        this.balance += amount;
    }
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        this.balance -= amount;
    }
    public void transfer(BankAccount account,double amount) throws InvalidAmountException, InsufficientBalanceException, AccountNotFound {
        account.withdraw(amount);
        this.deposit(amount);
    }
    public abstract void displayDetails();
    public double getBalance() {
        return balance;
    }
}
