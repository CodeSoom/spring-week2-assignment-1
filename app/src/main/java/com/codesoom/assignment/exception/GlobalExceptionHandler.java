package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code=HttpStatus.NOT_FOUND, reason = "No Such Task Found.")
    @ExceptionHandler(TaskNotFoundException.class)
    public void NoSuchTaskFound() { }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Method Argument Type Mismatch Exception")
    public void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    }
}
