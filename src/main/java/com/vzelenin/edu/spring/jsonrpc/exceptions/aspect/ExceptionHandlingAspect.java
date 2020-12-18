package com.vzelenin.edu.spring.jsonrpc.exceptions.aspect;

import com.vzelenin.edu.spring.jsonrpc.exceptions.resolver.ExceptionResolver;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    private final ExceptionResolver exceptionResolver;

    public ExceptionHandlingAspect(ExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Pointcut("execution(* com.vzelenin.edu.spring.jsonrpc.service.UserServiceImpl.*(..))")
    public void methodExecution() {
    }

    @AfterThrowing(pointcut = "methodExecution()", throwing = "ex")
    public void handleException(Exception ex) {
        exceptionResolver.resolve(ex);
    }
}
