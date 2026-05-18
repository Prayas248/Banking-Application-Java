package exception;

public class InsufficientBalanceException extends Exception {
    private String message;

    InsufficientBalanceException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
