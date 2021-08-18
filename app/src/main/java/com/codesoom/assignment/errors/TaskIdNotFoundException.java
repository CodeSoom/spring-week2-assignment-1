package com.codesoom.assignment.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskIdNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 ID의 task를 찾을 수 없습니다.";

    public TaskIdNotFoundException() {
        super(MESSAGE);
    }
}
