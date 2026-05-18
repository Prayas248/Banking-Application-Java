package exception;

public class InvalidEmailException extends Exception{
    private String message;

    public InvalidEmailException(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
