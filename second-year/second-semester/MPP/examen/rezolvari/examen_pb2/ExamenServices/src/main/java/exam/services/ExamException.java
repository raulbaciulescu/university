package exam.services;

public class ExamException extends Exception{
    public ExamException() {
    }

    public ExamException(String message) {
        super(message);
    }

    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }
}
