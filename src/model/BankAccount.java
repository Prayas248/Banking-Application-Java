package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public abstract class BankAccount extends Customer {
    private int accountNumber;
    private Customer customer;
    private double balance;

    public BankAccount() {
        super();
    }

    public BankAccount(int accountNumber, Customer customer, double balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public int getAccountNumber() { return accountNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public abstract void deposit(double amount) throws InvalidAmountException;
    public abstract void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException;
    public abstract void transfer(BankAccount targetAccount, double amount) throws InvalidAmountException, InsufficientBalanceException;
    public abstract void displayDetails();
}


