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

    /**
     * 태스크를 받아 리포지토리에 등록한다.
     * @param task 새로 추가할 태스크
     * @return 새로 추가된 태스크를 리턴한다.
     */
    public Task add(Task task) {
        task.setId(atomicID.incrementAndGet());
        this.tasks.putIfAbsent(atomicID.get(), task);
        return this.tasks.get(atomicID.longValue());
    }

    /**
     * 인자로 받은 task 과 같은 id 를 가진 태스크가 존재한다면, 인자로 받은 새 태스크로 기존 태스크를 수정한다
     * @param task 수정할 태스크
     * @return 수정된 태스크
     */
    public Task edit(Task task) {
        this.tasks.replace(task.getId(), task);
        return task;
    }

    /**
     * @param task 삭제할 태스크
     * @return 태스크가 존재했다면, 삭제된 태스크를 리턴하고, 존재하지 않는 다면 비어있는 Optional 을 리턴한다.
     */
    public Optional<Task> delete(Task task) {
        Long id = task.getId();
        return Optional.ofNullable(this.tasks.remove(id));
    }

    /**
     * 만약 id 를 가지고 있는 태스크가 존재하다면, 해당 태스크를 리턴한다.
     * @param id 태스크의 id
     * @return id 를 가지고 있는 태스크, 존재하지 않다면 Optional.empty()
     */
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(this.tasks.get(id));
    }

    /**
     * 모든 태스크를 리턴한다.
     * @return 태스크의 리스트
     */
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
}
