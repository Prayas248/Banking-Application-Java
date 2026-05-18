package model;

import util.TransactionList;

import java.time.LocalDateTime;

public class Transaction{
    private static int transactionId;
    private final double amount;
    private final PaymentType type;
    private final LocalDateTime timestamp;

    public Transaction(double amount, PaymentType type) {
        transactionId++;
        this.amount = amount;
        this.type = type;
        timestamp = new LocalDateTime.now();
        TransactionList.addTransaction(this);
    }

    public static int getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type=" + type +
                ", timestamp=" + timestamp +
                '}';
    }
}





