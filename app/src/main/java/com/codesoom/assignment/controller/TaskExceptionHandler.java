package com.codesoom.assignment.controller;

import com.codesoom.assignment.exception.TaskException;
import com.codesoom.assignment.exception.TaskInvalidTitleException;
import com.codesoom.assignment.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    public final String TASK_NOT_FOUND = "할 일을 찾을 수 없습니다. 입력하신 할 일의 id를 확인해주세요.";
    public final String INVALID_TITLE = "할 일의 제목을 확인해주세요";

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        return new ResponseEntity(TASK_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskInvalidTitleException.class)
    public ResponseEntity handleTaskInvalidTitleException() {
        return new ResponseEntity(INVALID_TITLE, HttpStatus.BAD_REQUEST);
    }
}
