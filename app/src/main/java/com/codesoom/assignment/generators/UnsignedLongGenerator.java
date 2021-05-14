package com.codesoom.assignment.generators;

import org.springframework.stereotype.Component;

@Component
class UnsignedLongGenerator extends IdGenerator{
    private Long currentIdx;

    public UnsignedLongGenerator() {
        this.currentIdx = 0L;
    }

    @Override
    public synchronized Long generateId() {
        this.currentIdx += 1;
        return this.currentIdx;
    }
}
