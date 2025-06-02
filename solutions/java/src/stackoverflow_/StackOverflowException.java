package stackoverflow_;

public class StackOverflowException extends RuntimeException {
    public StackOverflowException(String message) {
        super(message);
    }
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
