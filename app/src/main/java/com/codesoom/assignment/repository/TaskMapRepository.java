package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 작업과 관련된 컬렉션 기능을 제공하는 역할을 가지고 있습니다.
 *
 * 사용자의 요청에 따라 작업을 생성, 조회, 삭제 기능을 수행해야 하는 책임을 가지고 있습니다.
 */
@Repository
public class TaskMapRepository implements TaskRepository {
    private Long id = 0L;
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Optional<Task> get(Long taskId) {
        return Optional.ofNullable(taskMap.get(taskId));
    }

    public List<Task> getAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task add(String title) {
        Task task = new Task(id, title);
        taskMap.put(id++, task);
        return task;
    }

    public void remove(Long taskId) {
        taskMap.remove(taskId);
    }
}
