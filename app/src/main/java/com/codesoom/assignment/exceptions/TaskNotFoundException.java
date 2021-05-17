package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class TaskNotFoundException extends HttpClientErrorException {
    public TaskNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Task Not Found");
    }
}
