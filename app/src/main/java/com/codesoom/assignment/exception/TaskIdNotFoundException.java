package com.codesoom.assignment.exception;

import java.util.function.Supplier;

/**
 * 요청한 할 일 ID가 존재하지 않을경우 해당 예외가 발생한다.
 */
public class TaskIdNotFoundException extends RuntimeException {

    private long id;

    public TaskIdNotFoundException(long id) {
        super("[" + id + "] 는 존재하지않는 ID 입니다.");
    }

    public static Supplier<TaskIdNotFoundException> throwException(Long id) {
        return () -> new TaskIdNotFoundException(id);
    }
}
