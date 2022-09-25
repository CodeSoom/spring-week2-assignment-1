package com.codesoom.assignment.exception;

import java.util.function.Supplier;

/**
 * 요청한 할 일 ID가 존재하지 않을경우 해당 예외가 발생한다.
 */
public class TaskIdNotFoundException extends RuntimeException implements Supplier<TaskIdNotFoundException> {
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

    @Override
    public TaskIdNotFoundException get() {
        return new TaskIdNotFoundException(getMessage());
    }
}
