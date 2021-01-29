package com.codesoom.assignment.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code TaskControllerAdvice} is handling exception to http response
 *
 * @author etff
 * @version 1.0.0 21/01/29
 */
@ControllerAdvice
public class TaskControllerAdvice {

    /**
     * Response http not found error when catching NotFoundException.class
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handlerNotFound() {
    }
}
