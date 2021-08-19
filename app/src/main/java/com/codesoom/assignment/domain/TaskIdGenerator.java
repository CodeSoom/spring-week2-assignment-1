package com.codesoom.assignment.domain;

import org.springframework.stereotype.Component;

@Component
public class TaskIdGenerator implements IdGenerator{
    public static final Long DEFAULT_INCREASE_SEQUENCE = 1L;

    @Override
    public Long generate(Long source) {
        return source + DEFAULT_INCREASE_SEQUENCE;
    }
}
