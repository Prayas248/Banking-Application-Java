package exception;

public class InsufficientBalanceException extends Exception {
    private String message;

    public InsufficientBalanceException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
