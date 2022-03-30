package com.codesoom.assignment.common.exception;

import com.codesoom.assignment.common.RestResponse;
import com.codesoom.assignment.common.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 * @description : Controller, Service Layer 에서 발생하는 Exception들을 처리한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TaskNotFoundException.class)
    protected ResponseEntity<RestResponse> handleTaskNotFoundException(TaskNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestResponse(StatusCodes.NOT_FOUND));
    }

}
