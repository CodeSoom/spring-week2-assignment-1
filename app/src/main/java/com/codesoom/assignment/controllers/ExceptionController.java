package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.exceptions.TaskTitleEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({TaskTitleEmptyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String taskTitleMustNotBeBlank(final RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({TaskNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFound(final RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(final RuntimeException ex) {
        return ex.getMessage();
    }
}
