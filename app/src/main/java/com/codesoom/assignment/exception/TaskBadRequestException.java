package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TaskBadRequestException extends RuntimeException {

    public TaskBadRequestException() {
        super();
    }

    public TaskBadRequestException(String field) {
        super(field + " 이 비어있습니다");
    }

}
