package com.gogoasa.c.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("ILLEGAL ARGUMENT EXCEPTION OCCURRED: {}", exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException exception) {
        log.error("RUNTIME EXCEPTION OCCURRED: {}", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        log.error("EXCEPTION OCCURRED: {}", exception.getMessage());
    }
}
