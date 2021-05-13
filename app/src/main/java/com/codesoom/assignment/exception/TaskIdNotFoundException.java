package com.codesoom.assignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskIdNotFoundException extends RuntimeException {
    private final String message = "요청하신 Task를 찾을 수 없습니다.";

    public TaskIdNotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
