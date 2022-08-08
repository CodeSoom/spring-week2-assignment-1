package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        return new ErrorResponse(e.getMessage());
    }
}
