package com.codesoom.assignment.repository;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicIdGenerator implements IdGenerator {
    private final AtomicLong id = new AtomicLong();

    @Override
    public Long generate() {
        return id.incrementAndGet();
    }
}
