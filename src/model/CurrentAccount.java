package model;


import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public class CurrentAccount extends BankAccount implements PaymentType {

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(int accountNumber, Customer customer, double balance) {
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
