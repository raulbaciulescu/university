package utils;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String msg) {
        super(msg);
    }

    public SyntaxException() {
        super();
    }
}
