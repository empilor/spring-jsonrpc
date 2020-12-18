package com.vzelenin.edu.spring.jsonrpc.exceptions;

import com.vzelenin.edu.spring.jsonrpc.exceptions.exception.OwnException;
import org.springframework.stereotype.Component;

@Component
public class ServiceThrowsExceptions {
    public String getOwnException() {
        throw new OwnException("OwnException has been thrown");
    }

    public String getNullPointerException() {
        throw new NullPointerException("NullPointerException has been thrown");
    }


}
