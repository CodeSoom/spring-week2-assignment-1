package com.codesoom.assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundTaskExceptionHandler(TaskNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String unknownExceptionHandler(UnknownException e) {
        return e.getMessage();
    }
}
