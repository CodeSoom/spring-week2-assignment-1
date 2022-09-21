package com.codesoom.assignment.advice;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.exception.ErrorResult;
import com.codesoom.assignment.exception.TaskIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TaskController.class)
public class TaskControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> taskExceptionHandler(TaskIdNotFoundException taskIdNotFoundException) {
        ErrorResult errorResult = new ErrorResult("FAIL", taskIdNotFoundException.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }
}
