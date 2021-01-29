package com.codesoom.assignment.application;

/**
 * custom exception class check exceptions
 * that can be thrown during the task not found
 *
 * @author etff
 * @version 1.0.0 21/01/29
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Constructs a new task not found class exception with {@code null} as its
     * detail message.
     */
    public TaskNotFoundException() {
        super("Task를 찾을 수 없습니다.");
    }

    /**
     * Constructs a new task not found class exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new task not found class exception with the specified cause and a
     * detail message
     *
     * @param cause all errors and exceptions
     */
    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }
}
