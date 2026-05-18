package wallet;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.Customer;
import exception.AccountNotFound;
import util.FileLogger;

public interface WalletOperations {
    void addMoney(double amount) throws InvalidAmountException;
    void payBill(double amount) throws InsufficientBalanceException;
    void transferToWallet(Customer customer, double amount) throws Exception;
}

class PaytmWallet implements WalletOperations{
    FileLogger logger;
    Customer customer;
    double walletBalance;


    PaytmWallet(Customer customer){
        this.customer = customer;
    }

    @Override
    public void addMoney(double amount) throws InvalidAmountException {
        double updatedBalance = amount + this.walletBalance;
        if(walletBalance > 50000){
            try{
                logger = new FileLogger(customer);
                logger.log("Wallet has exceeded the maximum amount");
            }
            catch (Exception e){
                
            }

            throw new InvalidAmountException("Wallet has exceeded the maximum amount");
        }
        else{
            this.walletBalance = updatedBalance;
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
        }
    }
}


class PhonePeWallet implements WalletOperations{
    Customer customer;
    double walletBalance;

    PhonePeWallet(Customer customer){
        this.customer = customer;
    }

    @Override
    public void addMoney(double amount) throws InvalidAmountException {
        double updatedBalance = amount + this.walletBalance;
        if(walletBalance > 50000){
            throw new InvalidAmountException("Wallet has exceeded the maximum amount");
        }
        else{
            this.walletBalance = updatedBalance;
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
        }
    }
}
