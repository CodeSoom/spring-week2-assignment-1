package com.codesoom.assignment.services;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskIdGenerator implements IdGenerator{

    private AtomicLong taskId = new AtomicLong(1L);

    @Override
    public Long generate(){
        return taskId.getAndIncrement();
    }
}
