package com.codesoom.assignment.errors;

public class NotFoundTaskIDException extends RuntimeException {
    private final String message;

    public NotFoundTaskIDException() {
        super();
        this.message = "Not found task id";
    }

    public NotFoundTaskIDException(long id) {
        super();
        this.message = String.format("Not found task id: %d", id);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
