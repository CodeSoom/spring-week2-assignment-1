package com.codesoom.assignment.common.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
    private String exceptionMessage;
    private String requestDescription;

    public ErrorMessage(HttpStatus statusCode, LocalDateTime timestamp, String exceptionMessage, String requestDescription) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.exceptionMessage = exceptionMessage;
        this.requestDescription = requestDescription;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    public String getRequestDescription() {
        return requestDescription;
    }
}
