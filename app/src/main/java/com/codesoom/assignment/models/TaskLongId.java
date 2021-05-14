package com.codesoom.assignment.models;

/**
 * 할 일의 정수 식별자를 생성한다.
 */
public class TaskLongId implements IdGenerator<Long> {
    private Long taskId = 0L;

    @Override
    public synchronized Long newId() {
        this.taskId += 1;
        return taskId;
    }
}
