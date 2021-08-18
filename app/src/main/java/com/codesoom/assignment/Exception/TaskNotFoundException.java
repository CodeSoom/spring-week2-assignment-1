package com.codesoom.assignment.Exception;

/**
 * TaskNotFoundException은 taskManger에서 존재하지 않는 Task에 접근했을 때, 발생하는 Exception 입니다.
 *
 * @author wjy5446
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
