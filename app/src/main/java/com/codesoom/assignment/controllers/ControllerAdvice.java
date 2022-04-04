package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskInvalidFormatException;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *  Controller에서 발생하는 예외들을 처리합니다.
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(TaskNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        final ErrorResponse errorResponse
                = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskInvalidFormatException.class)
    protected ResponseEntity<ErrorResponse> handleTaskInvalidFormatException(TaskInvalidFormatException e) {
        final ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
