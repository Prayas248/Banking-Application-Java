package model;

import util.TransactionList;

import java.util.ArrayList;

public class Customer {
    private final int CustomerId;
    private String CustomerName;
    private String CustomerEmail;
    private String CustomerPhoneNumber;
    private ArrayList<Integer> transactionsOfCustomer;

    public Customer(int CustomerId,String CustomerName, String CustomerEmail, String CustomerPhoneNumber) {
        this.CustomerId = CustomerId;
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomerPhoneNumber = CustomerPhoneNumber;
        transactionsOfCustomer = new ArrayList<>();
    }

    public void addTransaction(double amount, PaymentType type) {
        Transaction curr = new Transaction(amount,type);
        transactionsOfCustomer.add(curr.getTransactionId());
    }
    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> transactionsAll = new ArrayList<>();
        for(Integer transactionId : transactionsOfCustomer){
            transactionsAll.add(TransactionList.getTransaction(transactionId));
        }
        return transactionsAll;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return CustomerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        CustomerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + CustomerId +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerEmail='" + CustomerEmail + '\'' +
                ", CustomerPhoneNumber='" + CustomerPhoneNumber + '\'' +
                '}';
    }
}
