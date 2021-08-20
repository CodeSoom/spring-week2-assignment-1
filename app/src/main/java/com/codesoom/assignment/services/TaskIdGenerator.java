package com.codesoom.assignment.services;

import org.springframework.stereotype.Service;

/**
 * 할 일 번호 생성을 담당합니다.
 */
@Service
public class TaskIdGenerator {

    private Long lastId = 0L;

    public Long getLastId() {
        increaseLastId();
        return lastId;
    }

    private synchronized void increaseLastId() {
        lastId++;
    }
}
