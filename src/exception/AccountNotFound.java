package exception;

public class AccountNotFound extends Exception {
    private String message;

    AccountNotFound(String message){
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
