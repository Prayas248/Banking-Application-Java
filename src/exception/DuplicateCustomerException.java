package exception;

public class DuplicateCustomerException extends Exception{
    private String message;

    DuplicateCustomerException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}