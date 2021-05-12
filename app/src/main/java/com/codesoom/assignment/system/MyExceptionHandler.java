package com.codesoom.assignment.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleDataNotFoundException(DataNotFoundException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
