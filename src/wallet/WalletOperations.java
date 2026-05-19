package wallet;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import exception.WalletLimitExceededException;
import model.Customer;

// Interface that all wallet types (Paytm, PhonePe) must implement
public interface WalletOperations {
    void addMoney(double amount) throws WalletLimitExceededException, InvalidAmountException;
    void payBill(double amount) throws InsufficientBalanceException, InvalidAmountException;
    // Transfer money to another customer's wallet of the same type
    void transferToWallet(Customer customer, double amount) throws Exception;
}


