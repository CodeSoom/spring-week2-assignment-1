package com.codesoom.assignment;

import javax.management.RuntimeErrorException;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id){
        super("Task not found: "+id);
    }
}
