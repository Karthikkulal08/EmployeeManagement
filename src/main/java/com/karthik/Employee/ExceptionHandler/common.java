package com.karthik.Employee.ExceptionHandler;

import com.karthik.Employee.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class common {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionModel> handleRuntimeException(RuntimeException ex) {
        // DO NOT pass 'ex' or 'ex.getCause()' directly into the model
        ExceptionModel error = new ExceptionModel();
        error.setMessage1(ex.getMessage().getBytes());
        error.setMessage2(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
