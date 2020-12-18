package com.vzelenin.edu.spring.jsonrpc.exceptions.exception;

public class OwnException extends RuntimeException {
    public OwnException() {
    }

    public OwnException(String message) {
        super(message);
    }

    public OwnException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnException(Throwable cause) {
        super(cause);
    }
}
