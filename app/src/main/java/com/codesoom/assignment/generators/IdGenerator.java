package com.codesoom.assignment.generators;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    private Long currentIdx;

    public IdGenerator() {
        this.currentIdx = 0L;
    }

    public synchronized Long generateId() {
        this.currentIdx += 1;
        return this.currentIdx;
    }
}
