package model;
import java.util.ArrayList;

import util.TransactionList;

import java.util.ArrayList;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private ArrayList<Integer> transactionsOfCustomer;

    public Customer(int customerId, String customerName, String customerEmail, String customerPhoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
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
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerId=" + customerId +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                '}';
    }
}
