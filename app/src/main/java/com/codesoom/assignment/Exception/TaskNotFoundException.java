package com.codesoom.assignment.Exception;

/**
 * 할 일을 찾지 못했을 때 던집니다.
 */

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }
}
