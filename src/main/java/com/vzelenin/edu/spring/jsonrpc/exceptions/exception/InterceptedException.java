package com.vzelenin.edu.spring.jsonrpc.exceptions.exception;

public class InterceptedException extends RuntimeException {
    public InterceptedException() {
    }

    public InterceptedException(String message) {
        super(message);
    }

    public InterceptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterceptedException(Throwable cause) {
        super(cause);
    }
}
