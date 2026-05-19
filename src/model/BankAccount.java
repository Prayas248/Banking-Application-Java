package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

// Base class for all bank account types (Savings, Current)
public abstract class BankAccount implements PaymentType{
    private int accountNumber;
    private Customer customer; // the owner of this account
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

    // Each account type must define its own deposit, withdraw, transfer, and display logic
    public abstract void deposit(double amount) throws InvalidAmountException;
    public abstract void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException;
    public abstract void transfer(BankAccount targetAccount, double amount) throws InvalidAmountException, InsufficientBalanceException;
    public abstract void displayDetails();
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", customer=" + customer.getCustomerName() +
                ", balance=" + balance +
                '}';
    }
}


