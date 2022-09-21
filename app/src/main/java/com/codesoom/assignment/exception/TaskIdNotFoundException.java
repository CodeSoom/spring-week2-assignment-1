package com.codesoom.assignment.exception;

/**
 * 요청한 할 일 ID가 존재하지 않을경우 해당 예외가 발생한다.
 */
public class TaskIdNotFoundException extends RuntimeException {
    public TaskIdNotFoundException() {
        super();
    }

    public TaskIdNotFoundException(String message) {
        super(message);
    }

    public TaskIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskIdNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TaskIdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
