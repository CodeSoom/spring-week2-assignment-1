package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.CustomBaseException;
import com.codesoom.assignment.models.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 예외 처리를 담당합니다.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomBaseException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> CustomBaseExceptionHandler(CustomBaseException e) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(e.getStatusCode()), e.getMessage(), e.validation);
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        return response;
    }

}
