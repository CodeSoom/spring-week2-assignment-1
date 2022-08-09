package com.codesoom.assignment.services;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskIdGenerator {

    private AtomicLong taskId = new AtomicLong(1L);

    public Long getNextId(){
        return taskId.getAndIncrement();
    }
}
