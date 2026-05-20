package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import util.TransactionList;

// Represents a bank customer who can hold multiple accounts and wallets
public class Customer extends CustomerService implements Cloneable {

    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    // Stores transaction IDs (not full objects) to look up from the global TransactionList
    private ArrayList<Integer> transactionsOfCustomer = new ArrayList<>();
    private static int customerCounter;
    // Maps account type name (e.g. "Savings", "Paytm") to the account object
    private HashMap<String, PaymentType> bankAccount = new HashMap<>();

    public Customer(){}

    public Customer(int customerId, String customerName, String customerEmail, String customerPhoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // Register a new account or wallet under a given type name
    public void addAccount(String type, PaymentType bankAccount){
        this.bankAccount.put(type, bankAccount);
    }

    public PaymentType getAccount(String type) {
        return bankAccount.get(type);
    }

    public PaymentType getAllAccounts() {

        ArrayList<Map.Entry<String, PaymentType>> accounts =
                new ArrayList<>(bankAccount.entrySet());
        boolean flag = true;
        while(flag) {
            int index = 1;
            for (Map.Entry<String, PaymentType> entry : accounts) {
                System.out.println(index + ". " + entry.getKey());
                index++;
            }
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose account: ");
            int choice = sc.nextInt();

            if (choice >= 1 && choice <= accounts.size()) {
                return accounts.get(choice - 1).getValue();
            } else {
                System.out.println("Wrong choice");
                System.out.println("Going ");
                flag=true;
            }
        }

        System.out.println("Invalid choice");
        return null;
    }

    // Returns any one account — used when switching customers to auto-select an account
    public PaymentType getFirstAccount() {
        if (!bankAccount.isEmpty()) {
            return bankAccount.values().iterator().next();
        }
        return null;
    }

    // Creates a transaction record and stores its ID for this customer
    public void addTransaction(double amount, String type, double balance) {
        Transaction curr = new Transaction(amount, type, balance);
        TransactionList.addTransaction(curr);
        transactionsOfCustomer.add(curr.getTransactionId());
    }

    // Fetches full transaction objects from the global store using stored IDs
    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> transactionsAll = new ArrayList<>();
        for(Integer transactionId : transactionsOfCustomer){
            transactionsAll.add(TransactionList.getTransaction(transactionId));
        }
        return transactionsAll;
    }

    // Shallow clone: shares the same transaction list and account map references
    public Customer shallowClone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    // Deep clone: creates new copies of the transaction list and account map
    @Override
    public Object clone() throws CloneNotSupportedException {
        Customer cloned = (Customer) super.clone();
        cloned.transactionsOfCustomer = new ArrayList<>(this.transactionsOfCustomer);
        cloned.bankAccount = new HashMap<>(this.bankAccount);
        return cloned;
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
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                '}';
    }
}
