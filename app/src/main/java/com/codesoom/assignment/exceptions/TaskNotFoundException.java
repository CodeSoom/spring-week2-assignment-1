package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;

/** id로 할 일을 찾지 못했을 경우 발생하는 exception */
public class TaskNotFoundException extends RuntimeException{

    private static final String MESSAGE_FORMAT = "%s에 해당하는 할 일을 찾을 수 없습니다.";
    private final String code;

    public TaskNotFoundException(Long id) {
        super(String.format(MESSAGE_FORMAT, id));
        this.code = String.valueOf(HttpStatus.NOT_FOUND.value());
    }

    public TaskNotFoundException(String code, Long id) {
        super(String.format(MESSAGE_FORMAT, id));
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
