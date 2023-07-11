package com.kayaspring.kayaspring.api.middlewares.exceptions;

import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ILogger logger;

    @Autowired
    public GlobalExceptionHandler(ILogger logService) {
        this.logger = logService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResultClass> handleException(Exception ex) {

        GenericResultClass genericResult = GenericResultClass.Exception(ex, logger);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResult);
    }

}



