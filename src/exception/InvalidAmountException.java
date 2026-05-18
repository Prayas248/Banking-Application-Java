package exception;

public class InvalidAmountException extends Exception {
    private String message;

    public InvalidAmountException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
