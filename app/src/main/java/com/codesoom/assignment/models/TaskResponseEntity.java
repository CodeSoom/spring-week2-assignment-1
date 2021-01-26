package com.codesoom.assignment.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TaskResponseEntity extends ResponseEntity {

    public TaskResponseEntity(Object body, HttpStatus status) {
        super(body, status);
    }

    public static <T> ResponseEntity created(T body) {
        return created().body(body);
    }

    public static BodyBuilder created() {
        return status(HttpStatus.CREATED);
    }
}
