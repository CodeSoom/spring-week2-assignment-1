package com.codesoom.assignment.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    private AtomicLong newId = new AtomicLong(0);

    public Long generateId() {
        return newId.incrementAndGet();
    }
}
