package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * 할 일을 찾지 못했을 때 던집니다.
 */
public class TaskNotFoundException extends CommonException {
    private static final String MESSAGE = "할 일이 존재하지 않습니다.";

    public TaskNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
