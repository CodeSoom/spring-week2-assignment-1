package com.codesoom.assignment.repository;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class AtomicIdGenerator implements IdGenerator {
    private final AtomicLong id = new AtomicLong();

    @Override
    public Long generate() {
        return id.incrementAndGet();
    }
}
