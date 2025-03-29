package com.comercio.sa.cliente.cad.exceptions;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RuntimeExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> runtimeErrorHandler(RuntimeException runtimeException){
        System.out.println(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.SC_SERVER_ERROR).body(runtimeException.getMessage());
    }
}
