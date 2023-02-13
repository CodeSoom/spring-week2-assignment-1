package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){
        super("Task Not Found");
    }
}
