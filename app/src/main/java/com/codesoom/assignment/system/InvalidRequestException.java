package com.codesoom.assignment.system;

// Exception Testing
public class InvalidRequestException extends DataNotFoundException {

    public InvalidRequestException() {
        super("Task");
    }
}
