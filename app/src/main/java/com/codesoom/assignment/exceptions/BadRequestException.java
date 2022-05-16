package com.codesoom.assignment.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Bad Request");
    }
}
