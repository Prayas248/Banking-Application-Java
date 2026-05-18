package exception;

public class InvalidPhoneNumberException extends Exception{
    private String message;

    InvalidPhoneNumberException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
