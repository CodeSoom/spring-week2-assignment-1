package com.codesoom.assignment.task.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.atomic.AtomicLong;

@UtilityClass
public class IdGenerator {

    private static final AtomicLong ID = new AtomicLong();

    public static long getIncrementId() {
        return ID.incrementAndGet();
    }

}
