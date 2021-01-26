package com.codesoom.assignment.errors;

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
