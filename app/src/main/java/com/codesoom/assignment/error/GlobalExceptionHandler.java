package com.codesoom.assignment.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleException(NullPointerException e){
        log.error("id :{}", e.getMessage());
        ErrorResponse response =ErrorResponse.of(ErrorCode.TASK_NULL);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdEmptyException.class)
    public ResponseEntity<ErrorResponse> handleException(IdEmptyException e){
        log.error("id :{}", e.getMessage());
        ErrorResponse response =ErrorResponse.of(ErrorCode.ID_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




}
