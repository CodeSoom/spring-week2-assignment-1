package com.codesoom.assignment.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final LocalDateTime localDateTime;
    private final String message;

    public ErrorResponse(String message) {
        this.localDateTime = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMessage() {
        return message;
    }
}
