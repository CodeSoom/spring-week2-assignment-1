package com.codesoom.assignment.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundTaskIDException extends RuntimeException {
    private final String message;
    private final String defaultMessage = "Not found task id";

    public NotFoundTaskIDException() {
        super();
        this.message = defaultMessage;
    }

    public NotFoundTaskIDException(long id) {
        super();
        this.message = String.format("%s: %d", defaultMessage, id);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
