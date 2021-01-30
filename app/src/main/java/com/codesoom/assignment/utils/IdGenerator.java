package com.codesoom.assignment.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * id를 생성하기 위한 클래스.
 */
@Component
public class IdGenerator {

    private AtomicLong newId = new AtomicLong(0);

    /**
     * id를 생성해 리턴합니다.
     *
     * @return 생성된 id
     */
    public Long generateId() {
        return newId.incrementAndGet();
    }

}
