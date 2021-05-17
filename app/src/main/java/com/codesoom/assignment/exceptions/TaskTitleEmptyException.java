package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class TaskTitleEmptyException extends HttpClientErrorException {
    public TaskTitleEmptyException() {
        super(HttpStatus.BAD_REQUEST, "Task's title must not be blank");
    }
}
