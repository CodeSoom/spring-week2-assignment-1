package com.codesoom.assignment.exception;

/**
 * 할 일을 찾을 수 없을 때 던집니다.
 */
public final class TaskNotFoundException extends RuntimeException {

    private static final String MESSAGE = "할 일을 찾을 수 없습니다.";

    public TaskNotFoundException() {
        super(MESSAGE);
    }
}
