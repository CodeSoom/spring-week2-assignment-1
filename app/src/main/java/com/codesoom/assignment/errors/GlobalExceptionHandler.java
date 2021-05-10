package com.codesoom.assignment.errors;

import com.codesoom.assignment.exceptions.DoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity handleEntityNotFoundException(DoesNotExistException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
