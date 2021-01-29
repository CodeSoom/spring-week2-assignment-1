package com.codesoom.assignment.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 할 일들의 예외처리를 Http 상태로 리턴하는 클래스.
 *
 * @author etff
 * @version 1.0.0 21/01/29
 */
@ControllerAdvice
public class TaskControllerAdvice {

    /**
     * TaskNotFoundException 이 발생하면 Http Not Found 상태를 리턴합니다.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public void handlerNotFound() {
    }
}
