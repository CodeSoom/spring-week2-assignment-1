package com.codesoom.assignment.repositories;

import com.codesoom.assignment.domains.Task;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 *  DB 저장방식을 사용하지 않는 동안 임시로 사용할 In Memory 리포지토리 입니다.
 */
@Repository
public class InMemoryTaskRepositoryImpl implements TaskRepository {

    private static AtomicLong maxId = new AtomicLong(0);
    private static Map<Long, Task> tasks = new ConcurrentHashMap<>();

    /** id를 증가시킨 후 반환합니다. */
    @Override
    public Long generateId() {
        return maxId.incrementAndGet();
    }

    /** 모든 할 일을 반환합니다. */
    @Override
    public Map<Long, Task> getTasks() {
        return tasks;
    }

    /** 새로운 할 일을 저장합니다. */
    @Override
    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    /** id와 매핑되는 할 일을, 없으면 null을 반환합니다.*/
    @Override
    public Task findById(Long id) {
        return tasks.get(id);
    }

    /** id와 매핑되는 할 일을 수정 후 변경 결과를 반환합니다. */
    @Override
    public void update(Long id, Task updatedTask) {
        tasks.replace(id, updatedTask);
    }

    /** id와 매핑된 할 일을 삭제합니다. */
    @Override
    public void remove(Long id) {
        tasks.remove(id);
    }

}
