package wallet;

import exception.InsufficientBalanceException;
import exception.WalletLimitExceededException;
import model.Customer;

public interface WalletOperations {
    void addMoney(double amount) throws WalletLimitExceededException;
    void payBill(double amount) throws InsufficientBalanceException;
    void transferToWallet(Customer customer, double amount) throws Exception;
}


