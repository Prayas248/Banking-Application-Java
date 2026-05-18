package wallet;

public interface WalletOperations {
    void addMoney(double balance);
    void payBill(double balance);
    void transferToWallet(double balance);
}
