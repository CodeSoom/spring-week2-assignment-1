package com.codesoom.assignment.application;

/**
 * {@code NotFoundException} is custom exception class
 * exceptions that can be thrown during the task class not found
 *
 * @author etff
 * @version 1.0.0 21/01/29
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new not found class exception with {@code null} as its
     * detail message.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructs a new not found class exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new not found class exception with the specified cause and a
     * detail message
     *
     * @param cause all errors and exceptions
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
