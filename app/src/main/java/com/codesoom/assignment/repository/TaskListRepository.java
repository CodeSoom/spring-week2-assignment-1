package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 작업과 관련된 컬렉션 기능을 제공하는 역할을 가지고 있습니다.
 *
 * 사용자의 요청에 따라 작업을 생성, 조회, 삭제 기능을 수행해야 하는 책임을 가지고 있습니다.
 */
public class TaskListRepository implements TaskRepository {
    private Long id = 0L;
    private final List<Task> taskList = new CopyOnWriteArrayList<>();

    @Override
    public Optional<Task> get(Long taskId) {
        return taskList.stream()
                .filter(t -> t.isSame(taskId))
                .findFirst();
    }

    public List<Task> getAll() {
        return taskList;
    }

    @Override
    public Task add(String title) {
        Task task = new Task(id++, title);
        taskList.add(task);
        return task;
    }

    @Override
    public void remove(Long taskId) {
        taskList.removeIf(t -> t.isSame(taskId));
    }
}
