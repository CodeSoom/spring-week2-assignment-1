package com.codesoom.assignment.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskIdGenerator {

    private final AtomicLong atomicLong = new AtomicLong(1L);

    private TaskIdGenerator() {
    }

    public Long allocateId() {
        return atomicLong.getAndIncrement();
    }
}
