package exception;

public class InvalidAmountException extends Exception {
    private String message;

    InvalidAmountException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
