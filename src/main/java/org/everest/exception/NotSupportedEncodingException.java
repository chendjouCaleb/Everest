package org.everest.exception;

import java.io.UnsupportedEncodingException;

/**
 * No verified exception to wrapped the {@code java.io.UnsupportedEncodingException}
 */
public class NotSupportedEncodingException extends RuntimeException {

    public NotSupportedEncodingException(UnsupportedEncodingException e){
        super(e);
    }
}
