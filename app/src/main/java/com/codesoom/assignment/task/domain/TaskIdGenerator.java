package com.codesoom.assignment.task.domain;

import java.util.concurrent.atomic.AtomicLong;

public class TaskIdGenerator {
    private static final AtomicLong taskId = new AtomicLong(1L);

    private TaskIdGenerator() {
    }

    /**
     * 할 일의 id를 1 증가 후, 반환합니다.
     * @return 할 일의 신규 id 반환
     */
    public static Long createId() {
        return taskId.getAndIncrement();
    }
}
