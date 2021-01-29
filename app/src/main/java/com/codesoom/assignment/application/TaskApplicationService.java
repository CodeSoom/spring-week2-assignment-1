package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.optional.Empty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskApplicationService {
    private final Map<Long, Task> taskMap = new HashMap<>();
    private Long lastId = 1L;

    public List<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>(taskMap.values());
        orderTaskById(allTasks);
        return allTasks;
    }

    public Long createTask(String title) {
        Long id = getNextId();
        Task newTask = new Task(id, title);
        taskMap.put(id, newTask);
        return id;
    }

    public Optional<Task> findTask(Long taskId) {
        if (!taskMap.containsKey(taskId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(taskMap.get(taskId));
    }

    private Long getNextId() {
        return lastId++;
    }

    public Optional<Empty> updateTaskTitle(Long taskId, String newTitle) {
        Optional<Task> targetTask = findTask(taskId).map(
                it -> it.updateTaskTitle(newTitle)
        );
        return putTaskToMap(targetTask);
    }

    private Optional<Empty> putTaskToMap(Optional<Task> task) {
        if (task.isEmpty()) {
            return Optional.empty();
        }
        Task targetTask = task.get();
        taskMap.put(targetTask.getId(), targetTask);
        return Optional.of(Empty.empty);
    }

    public Optional<Empty> deleteTask(Long taskId) {
        return deleteTask(findTask(taskId));
    }

    private Optional<Empty> deleteTask(Optional<Task> task) {
        if (task.isEmpty()) {
            return Optional.empty();
        }
        Task targetTask = task.get();
        taskMap.remove(targetTask.getId());
        return Optional.of(Empty.empty);
    }

    private void orderTaskById(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getId));
    }
}
