package services;

public class PurchaseException extends Exception {
    public PurchaseException() {
    }

    public PurchaseException(String message) {
        super(message);
    }

    public PurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
