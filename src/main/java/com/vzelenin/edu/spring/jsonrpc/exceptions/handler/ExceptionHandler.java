package com.vzelenin.edu.spring.jsonrpc.exceptions.handler;

import com.vzelenin.edu.spring.jsonrpc.exceptions.exception.OwnException;

public interface ExceptionHandler {
    void handle(OwnException ex);
    void handleUnknown(RuntimeException ex);
}
