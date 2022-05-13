package com.codesoom.assignment.controller;

import com.codesoom.assignment.exception.TaskException;
import com.codesoom.assignment.exception.TaskInvalidTitleException;
import com.codesoom.assignment.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskExceptionHandler {

    public final String TASK_NOT_FOUND = "할 일을 찾을 수 없습니다. 입력하신 할 일의 id를 확인해주세요.";
    public final String INVALID_TITLE = "할 일의 제목을 확인해주세요";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public String handleNotFoundException() {
        return TASK_NOT_FOUND;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskInvalidTitleException.class)
    public String handleTaskInvalidTitleException() {
        return INVALID_TITLE;
    }
}
