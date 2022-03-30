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

    private static AtomicLong maxId = new AtomicLong();
    private static Map<Long, Task> tasks = new ConcurrentHashMap<>();

    /**
     * 증가된 id를 반환합니다.
     */
    @Override
    public Long generateId() {
        return maxId.incrementAndGet();
    }

    @Override
    public Map<Long, Task> getTasks() {
        return tasks;
    }

    @Override
    public Task save(Task task) {
        return tasks.put(task.getId(), task);
    }

    /**
     * id와 매핑되는 값이 있으면 Task를, 없으면 null을 반환합니다.
     */
    @Override
    public Task findById(Long id) {
        return tasks.get(id);
    }

    @Override
    public Task update(Long id, Task updatedTask) {
        return tasks.replace(id, updatedTask);
    }

    @Override
    public void remove(Long id) {
        tasks.remove(id);
    }

}
