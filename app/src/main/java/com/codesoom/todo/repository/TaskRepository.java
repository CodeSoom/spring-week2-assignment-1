package com.codesoom.todo.repository;

import com.codesoom.todo.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {

    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong atomicID = new AtomicLong(0);

    public Task add(Task task) {
        task.setId(atomicID.incrementAndGet());
        this.tasks.putIfAbsent(atomicID.get(), task);
        return this.tasks.get(atomicID.longValue());
    }

    /**
     * 인자로 받은 task 과 같은 id 를 가진 태스크가 존재한다면, 인자로 받은 새 태스크로 기존 태스크를 수정한다
     *
     * @param task 수정할 태스크
     * @return 수정된 태스크
     */
    public Task edit(Task task) {
        this.tasks.replace(task.getId(), task);
        return task;
    }

    /**
     * @param task is target to be deleted
     * @return deleted task if task with id exist, else, Optional object with null
     */
    public Optional<Task> delete(Task task) {
        Long id = task.getId();
        return Optional.ofNullable(this.tasks.remove(id));
    }

    // TODO: Javadoc
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(this.tasks.get(id));
    }

    // TODO: Javadoc
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
}
