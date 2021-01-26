package com.codesoom.assignment.application;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final int START_ID = 0;
    AtomicLong idCounter = new AtomicLong(START_ID);

    public long generateNewTaskId() {
        return idCounter.getAndIncrement();
    }

    public void resetId() {
        idCounter.set(0);
    }
}
