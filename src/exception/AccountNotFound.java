package exception;

public class AccountNotFound extends Exception {
    private String message;

    public AccountNotFound(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
