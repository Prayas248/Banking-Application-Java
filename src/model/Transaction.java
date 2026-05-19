package model;

import util.TransactionList;

import java.time.LocalDateTime;


// Represents a single financial transaction (deposit, withdrawal, or transfer)
public class Transaction{
    private static int counter; // auto-incremented to give each transaction a unique ID
    private Integer transactionId;
    private final double amount;
    private String type; // account type involved: "Savings", "Current", "Paytm", "PhonePe"
    private LocalDateTime timestamp; // exact date and time the transaction occurred
    private double balance; // account balance after this transaction

    public Transaction(double amount, String type, double balance) {
        this.transactionId = counter++;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.balance = balance;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id: " + transactionId +
                ", amount=" + amount +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", Balance=" + balance +
                '}';
    }
}





