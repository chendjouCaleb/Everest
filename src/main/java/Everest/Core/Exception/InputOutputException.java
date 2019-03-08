package Everest.Core.Exception;

import java.io.IOException;

public class InputOutputException extends RuntimeException{
    private IOException ioException;

    public InputOutputException(IOException ioException) {
        this.ioException = ioException;
    }

    public InputOutputException(IOException ioException, String message) {
        super(message);
        this.ioException = ioException;
    }

    public InputOutputException(String message, Throwable cause, IOException ioException) {
        super(message, cause);
        this.ioException = ioException;
    }

    public InputOutputException(Throwable cause, IOException ioException) {
        super(cause);
        this.ioException = ioException;
    }

    public InputOutputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, IOException ioException) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.ioException = ioException;
    }

    public IOException getIoException() {
        return ioException;
    }
}
