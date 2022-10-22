package com.codesoom.assignment.task.domain;

import java.util.concurrent.atomic.AtomicLong;

public class TaskIdGenerator {
    private static final AtomicLong taskId = new AtomicLong(1L);

    private TaskIdGenerator() {
    }

    public static Long createId() {
        return taskId.getAndIncrement();
    }
}
