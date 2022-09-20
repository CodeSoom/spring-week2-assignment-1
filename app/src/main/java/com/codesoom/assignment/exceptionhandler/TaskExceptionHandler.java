package com.codesoom.assignment.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외가 발생하면 알맞은 응답을 보내도록 처리
 */
@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentError() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
