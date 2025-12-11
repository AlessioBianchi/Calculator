package exception;

public class DivisionByZeroException extends RuntimeException{

    String message;

    public DivisionByZeroException(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
