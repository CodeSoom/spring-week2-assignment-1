package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.exceptions.TaskTitleEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({TaskTitleEmptyException.class})
    public ResponseEntity<String> taskTitleMustNotBeBlank(final RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<String> taskNotFound(final RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<String> internalServerError(final RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
