package com.codesoom.assignment.application;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
@Scope(scopeName = "prototype")
public class TaskIdGenerator implements IdGenerable {
    private static final int START_ID = 0;
    private final AtomicLong idCounter = new AtomicLong(START_ID);

    @Override
    public long generateNewTaskId() {
        return idCounter.getAndIncrement();
    }

    @Override
    public void resetId() {
        idCounter.set(START_ID);
    }
}
