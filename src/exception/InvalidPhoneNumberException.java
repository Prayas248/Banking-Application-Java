package exception;

public class InvalidPhoneNumberException extends Exception{
    private String message;

    public InvalidPhoneNumberException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
