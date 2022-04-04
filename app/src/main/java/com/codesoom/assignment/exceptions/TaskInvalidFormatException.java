package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;

/** 유효하지 않은 형식의 할 일 추가 요청 시 발생하는 exception */
public class TaskInvalidFormatException extends RuntimeException{

    private String code;

    public TaskInvalidFormatException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
    }

    public TaskInvalidFormatException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
