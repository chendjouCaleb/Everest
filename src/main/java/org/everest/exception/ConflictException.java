package org.everest.exception;

public class ConflictException extends IllegalArgumentException {
    public ConflictException() {
    }

    public ConflictException(String s) {
        super(s);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
