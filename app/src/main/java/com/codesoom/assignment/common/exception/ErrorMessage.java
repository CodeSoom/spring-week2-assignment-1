package com.codesoom.assignment.common.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;

    public ErrorMessage(HttpStatus statusCode, LocalDateTime timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDescription() {
        return description;
    }
}
