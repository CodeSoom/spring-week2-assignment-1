package com.codesoom.assignment.generator;

import com.codesoom.assignment.interfaces.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskIdGenerator implements IdGenerator<Long> {

    private AtomicLong taskId = new AtomicLong(1L);

    @Override
    public Long generate(){
        return taskId.getAndIncrement();
    }
}
