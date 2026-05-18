package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public abstract class BankAccount {
    private int accountNumber;
    private Customer customer;
    private double balance;

    public BankAccount() {
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

class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 0;

    SavingsAccount() {
        super();
    }

    SavingsAccount(int accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }
        setBalance(getBalance() + amount);
        System.out.println("Deposited ₹" + amount + " | New Balance: ₹" + getBalance());
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Savings account must maintain a minimum balance of ₹" + MIN_BALANCE
            );
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn ₹" + amount + " | New Balance: ₹" + getBalance());
    }

    @Override
    public void transfer(BankAccount targetAccount, double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (this.getAccountNumber() == targetAccount.getAccountNumber()) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be greater than zero");
        }
        withdraw(amount);
        targetAccount.deposit(amount);
        System.out.println("Transferred ₹" + amount + " to Account #" + targetAccount.getAccountNumber());
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Savings Account ---");
        System.out.println("Account Number : " + getAccountNumber());
        System.out.println("Customer       : " + getCustomer().getCustomerName());
        System.out.println("Balance        : ₹" + getBalance());
        System.out.println("Minimum Balance: ₹" + MIN_BALANCE);
    }
}

class CurrentAccount extends BankAccount {

    CurrentAccount() {
        super();
    }

    CurrentAccount(int accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }
        setBalance(getBalance() + amount);
        System.out.println("Deposited ₹" + amount + " | New Balance: ₹" + getBalance());
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }
        if (amount > getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance. Available: ₹" + getBalance());
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn ₹" + amount + " | New Balance: ₹" + getBalance());
    }

    @Override
    public void transfer(BankAccount targetAccount, double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (this.getAccountNumber() == targetAccount.getAccountNumber()) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be greater than zero");
        }
        withdraw(amount);
        targetAccount.deposit(amount);
        System.out.println("Transferred ₹" + amount + " to Account #" + targetAccount.getAccountNumber());
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Current Account ---");
        System.out.println("Account Number : " + getAccountNumber());
        System.out.println("Customer       : " + getCustomer().getCustomerName());
        System.out.println("Balance        : ₹" + getBalance());
    }
}
