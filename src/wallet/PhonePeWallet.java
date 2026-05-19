package wallet;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import exception.WalletLimitExceededException;
import model.Customer;
import model.PaymentType;

// PhonePe wallet with a ₹50,000 maximum balance limit
public class PhonePeWallet implements WalletOperations, PaymentType {
    Customer customer;
    double walletBalance;

    public PhonePeWallet(Customer customer) {
        this.customer = customer;
        System.out.println("PhonePe Wallet created successfully for " + customer.getCustomerName());
    }

    @Override
    public void addMoney(double amount) throws WalletLimitExceededException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to add must be greater than zero");
        }
        double updatedBalance = amount + this.walletBalance;
        // Wallet cannot exceed ₹50,000
        if (updatedBalance > 50000) {
            throw new WalletLimitExceededException("Wallet has exceeded the maximum amount");
        }
        this.walletBalance = updatedBalance;
        this.customer.addTransaction(amount, "PhonePe", this.walletBalance);
        System.out.println("Added ₹" + amount + " to PhonePe wallet | New Balance: ₹" + this.walletBalance);
    }

    @Override
    public void payBill(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Bill amount must be greater than zero");
        }
        double updatedBalance = this.walletBalance - amount;
        if (updatedBalance < 0) {
            throw new InsufficientBalanceException("The amount is too high than the balance");
        }
        this.walletBalance = updatedBalance;
        this.customer.addTransaction(amount, "PhonePe", this.walletBalance);
        System.out.println("Bill of ₹" + amount + " paid via PhonePe | Remaining Balance: ₹" + this.walletBalance);
    }

    @Override
    public void transferToWallet(Customer receiver, double amount) throws Exception {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be greater than zero");
        }
        if (receiver == null || receiver.getCustomerName() == null) {
            throw new AccountNotFound("The transfer account is not found please select correct account.");
        }
        double updatedBalance = this.walletBalance - amount;
        if (updatedBalance < 0) {
            throw new InsufficientBalanceException("The amount is high than the balance");
        }
        // Receiver must also have a PhonePe wallet — cross-wallet transfers are not supported
        PaymentType receiverWallet = receiver.getAccount("PhonePe");
        if (!(receiverWallet instanceof PhonePeWallet)) {
            throw new AccountNotFound("Receiver does not have a PhonePe wallet.");
        }
        this.walletBalance = updatedBalance;
        this.customer.addTransaction(amount, "PhonePe", this.walletBalance);
        ((PhonePeWallet) receiverWallet).addMoney(amount);
        System.out.println("Transferred ₹" + amount + " to " + receiver.getCustomerName() + "'s PhonePe wallet | Your Balance: ₹" + this.walletBalance);
    }
}
