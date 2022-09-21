package com.codesoom.assignment.exceptions;

/**
 * 존재하지 않는 Task인 경우 던져진다.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("존재하지 않는 Task Id입니다.");
    }
}
