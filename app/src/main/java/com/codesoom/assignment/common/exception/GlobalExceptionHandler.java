package com.codesoom.assignment.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RestApiException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(RestApiException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
