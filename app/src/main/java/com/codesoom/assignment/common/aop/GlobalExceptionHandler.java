package com.codesoom.assignment.common.aop;

import com.codesoom.assignment.common.exceptions.TaskNotFoundException;
import com.codesoom.assignment.controllers.TaskController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외가 던져졌을 떄, 해당 예외에 맞는 Response를 반환합니다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // taskId에 맞는 할 일을 못찾은 경우
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity handleTaskNotFoundException(TaskNotFoundException ex){

        return new ResponseEntity(ex.getErroeMsg(), HttpStatus.NOT_FOUND);

    }

}
