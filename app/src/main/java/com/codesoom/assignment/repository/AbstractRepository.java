package com.codesoom.assignment.repository;

/**
 * 각 도메인의 Repository의 중복을 제거하기 위한 클래스이다.
 */
public abstract class AbstractRepository {
    private final IdGenerator idGenerator = new AtomicIdGenerator();

    protected Number generateId() {
        return idGenerator.generate();
    }
}
