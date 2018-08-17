package org.everest.exception;

public class NotAuthorizedAccessException extends IllegalAccessException {
    public NotAuthorizedAccessException() {
    }

    public NotAuthorizedAccessException(String s) {
        super(s);
    }
}
