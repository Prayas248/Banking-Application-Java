package model;

public class SavingAccount extends BankAccount{
    private final double rate = 4;
    public SavingAccount(String accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    public void displayDetails() {

    }
    public double getInterestRate() {
        double bal = getBalance();
        return (bal * rate)/100.00;
    }
}
