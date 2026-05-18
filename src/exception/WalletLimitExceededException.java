package exception;

public class WalletLimitExceededException extends Exception {
    private String message;

    WalletLimitExceededException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
