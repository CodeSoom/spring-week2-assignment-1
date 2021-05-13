package com.codesoom.assignment.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = new Date();
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse(String message, String details) {
        this(new Date(), message, details);
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
