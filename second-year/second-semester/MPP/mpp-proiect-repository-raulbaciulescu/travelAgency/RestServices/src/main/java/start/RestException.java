package start;


public class RestException extends RuntimeException {
    public RestException(Exception e) {
        super(e);
    }

    public RestException(String message) {
        super(message);
    }
}
