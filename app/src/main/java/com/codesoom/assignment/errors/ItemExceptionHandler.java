package com.codesoom.assignment.errors;

import com.codesoom.assignment.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity handleItemNotFoundException(ItemNotFoundException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
