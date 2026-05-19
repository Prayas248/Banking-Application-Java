package model;

import util.TransactionList;

import java.time.LocalDateTime;


public class Transaction{
    private static int counter;
    private Integer transactionId;
    private final double amount;
//    private final PaymentType type;
    private String type;
    private LocalDateTime timestamp;
    private double balance;

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





