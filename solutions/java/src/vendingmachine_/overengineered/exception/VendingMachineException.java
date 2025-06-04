package vendingmachine_.overengineered.exception;

public class VendingMachineException extends RuntimeException {
    public VendingMachineException(String message) {
        super(message);
    }
    public VendingMachineException(String message, Throwable cause) {
        super(message, cause);
    }
}
