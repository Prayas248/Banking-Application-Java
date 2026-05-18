package exception;

public class InvalidEmailException extends Exception{
    private String message;

    InvalidEmailException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
