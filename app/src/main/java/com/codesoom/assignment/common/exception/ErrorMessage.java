package com.codesoom.assignment.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {
    private HttpStatus statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(HttpStatus statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDescription() {
        return description;
    }
}
