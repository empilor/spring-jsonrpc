package com.vzelenin.edu.spring.jsonrpc.exceptions.handler;

import com.vzelenin.edu.spring.jsonrpc.exceptions.exception.InterceptedException;
import com.vzelenin.edu.spring.jsonrpc.exceptions.exception.OwnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerImpl implements ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerImpl.class);

    @Override
    public void handle(OwnException ex) {
        LOGGER.error("OwnException has been handled", ex);
        throw ex;
    }

    @Override
    public void handleUnknown(RuntimeException ex) {
        LOGGER.error("Unknown RuntimeException has been handled", ex);
        throw new InterceptedException("RuntimeException has been caught, message: " + ex.getMessage(), ex);
    }
}
