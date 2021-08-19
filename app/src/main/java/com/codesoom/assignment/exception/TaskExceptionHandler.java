package com.codesoom.assignment.exception;

import com.codesoom.assignment.model.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TaskExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<TaskResponse> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

