package com.codesoom.assignment.models;

public class ErrorResult {
    private String message;
    public ErrorResult(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
