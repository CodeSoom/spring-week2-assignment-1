package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.custom.InvalidTaskRequest;
import com.codesoom.assignment.exception.custom.TaskNotFound;
import com.codesoom.assignment.models.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 예외 처리를 담당합니다.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(TaskNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse TaskNotFoundExceptionHandler(TaskNotFound e) {
        return e.toErrorResponse();
    }

    @ExceptionHandler(InvalidTaskRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse InvalidTaskExceptionHandler(InvalidTaskRequest e) {
        return e.toErrorResponse();
    }

}
