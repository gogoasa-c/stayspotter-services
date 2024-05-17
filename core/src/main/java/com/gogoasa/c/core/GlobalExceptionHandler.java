package com.gogoasa.c.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        String errorMessage = STR."ILLEGAL ARGUMENT EXCEPTION OCCURRED: \{exception.getMessage()}";
        
        log.error(Arrays.toString(exception.getStackTrace()));

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        String errorMessage = STR."RUNTIME EXCEPTION OCCURRED: \{exception.getMessage()}";

        log.error(Arrays.toString(exception.getStackTrace()));

        return ResponseEntity.internalServerError().body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        String errorMessage = STR."EXCEPTION OCCURRED: \{exception.getMessage()}";

        log.error(Arrays.toString(exception.getStackTrace()));

        return ResponseEntity.internalServerError().body(errorMessage);
    }
}
