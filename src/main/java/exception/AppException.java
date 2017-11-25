package exception;

import javax.servlet.ServletException;

public class AppException extends ServletException{
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public AppException(Throwable rootCause) {
        super(rootCause);
    }
}
