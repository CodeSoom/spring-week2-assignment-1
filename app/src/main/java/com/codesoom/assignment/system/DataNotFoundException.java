package com.codesoom.assignment.system;

import org.springframework.http.HttpStatus;

// Exception Testing
public class DataNotFoundException extends AbstractException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(Throwable e) {
        super(e);
    }

    public DataNotFoundException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

