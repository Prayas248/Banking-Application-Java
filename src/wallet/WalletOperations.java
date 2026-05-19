package wallet;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.WalletLimitExceededException;
import model.Customer;
import model.PaymentType;
import util.FileLogger;

public interface WalletOperations {
    void addMoney(double amount) throws WalletLimitExceededException;
    void payBill(double amount) throws InsufficientBalanceException;
    void transferToWallet(Customer customer, double amount) throws Exception;
}

class PaytmWallet implements WalletOperations, PaymentType{
    FileLogger logger;
    Customer customer;
    double walletBalance;


    PaytmWallet(Customer customer){
        this.customer = customer;
    }

    @Override
    public void addMoney(double amount) throws WalletLimitExceededException {
        double updatedBalance = amount + this.walletBalance;
        if(updatedBalance > 50000){
            logger = new FileLogger(customer);
            logger.log("Wallet has exceeded the maximum amount");
            throw new WalletLimitExceededException("Wallet has exceeded the maximum amount");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }

    @Override
    public void payBill(double amount) throws InsufficientBalanceException{
        double updatedBalance = this.walletBalance - amount;
        if(updatedBalance < 0){
            throw new InsufficientBalanceException("The amount is too high than the balance");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }

    @Override
    public void transferToWallet(Customer customer,double amount) throws Exception {
        double updatedBalance = this.walletBalance - amount;
        if(customer.getCustomerName() == null){
            throw new AccountNotFound("The transfer account is not found please select correct account.");
        }
        else if(updatedBalance < 0){
            throw new InsufficientBalanceException("The amount is  high than the balance");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }
}


class PhonePeWallet implements WalletOperations, PaymentType{
    Customer customer;
    double walletBalance;

    PhonePeWallet(Customer customer){
        this.customer = customer;
    }

    @Override
    public void addMoney(double amount) throws WalletLimitExceededException {
        double updatedBalance = amount + this.walletBalance;
        if(updatedBalance > 50000){
            throw new WalletLimitExceededException("Wallet has exceeded the maximum amount");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }

    @Override
    public void payBill(double amount) throws InsufficientBalanceException{
        double updatedBalance = this.walletBalance - amount;
        if(updatedBalance < 0){
            throw new InsufficientBalanceException("The amount is too high than the balance");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }

    @Override
    public void transferToWallet(Customer customer,double amount) throws Exception {
        double updatedBalance = this.walletBalance - amount;
        if(customer.getCustomerName() == null){
            throw new AccountNotFound("The transfer account is not found please select correct account.");
        }
        else if(updatedBalance < 0){
            throw new InsufficientBalanceException("The amount is  high than the balance");
        }
        else{
            this.walletBalance = updatedBalance;
            this.customer.addTransaction(amount, "Current", this.walletBalance);
        }
    }
}
