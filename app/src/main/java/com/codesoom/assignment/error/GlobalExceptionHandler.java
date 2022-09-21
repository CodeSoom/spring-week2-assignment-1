package com.codesoom.assignment.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNullException.class)
    public ResponseEntity<ResponseMessage> handleException(TaskNullException e){
        return new ResponseEntity<>(ResponseMessage.TASK_NULL, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdEmptyException.class)
    public ResponseEntity<ResponseMessage> handleException(IdEmptyException e){

        return new ResponseEntity<>(ResponseMessage.ID_NOT_FOUND, HttpStatus.NOT_FOUND);
    }




}
