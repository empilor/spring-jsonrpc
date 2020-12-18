package com.vzelenin.edu.spring.jsonrpc.exceptions.resolver;

import com.vzelenin.edu.spring.jsonrpc.exceptions.handler.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionResolver {

    private final ExceptionHandler exceptionHandler;
    private final Map<Class, Method> handlerMethods = new HashMap<>();

    public ExceptionResolver(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @PostConstruct
    private void init() {
        Method[] declaredMethods = ReflectionUtils.getDeclaredMethods(exceptionHandler.getClass());
        for (Method method : declaredMethods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                Class<?> clazz = parameter.getType();
                handlerMethods.put(clazz, method);
            }
        }
    }

    public void resolve(Exception ex) {
        //Resolve known exception
        Class<?> exClass = ex.getClass();
        if (handlerMethods.containsKey(exClass)) {
            invokeExceptionHandling(ex, exClass);
        }

        //otherwise check if current exception is an ancestor of RuntimeException
        Class<RuntimeException> runtimeExClass = RuntimeException.class;
        if (runtimeExClass.isAssignableFrom(exClass)) {
            invokeExceptionHandling(ex, runtimeExClass);
        }
    }

    private void invokeExceptionHandling(Exception ex, Class<?> exClass) {
        Method method = handlerMethods.get(exClass);
        ReflectionUtils.invokeMethod(method, exceptionHandler, ex);
    }
}
