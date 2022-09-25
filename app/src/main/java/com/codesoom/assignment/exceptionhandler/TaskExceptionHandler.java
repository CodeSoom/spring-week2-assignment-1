package com.codesoom.assignment.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 할 일과 관련된 작업중에 발생하는 예외에 대한 응답을 처리합니다.
 */
@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentError() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
