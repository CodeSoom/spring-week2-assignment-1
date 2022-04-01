package com.codesoom.assignment.models;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task source) {
        Task task = findTask(id);
        task.setTitle(source.getTitle());

        return task;
    }

    public Task findTask(Long id) {
        return  tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tasks에 없는 id를 입력하여 Task를 찾을 수 없습니다." +
                        "Tasks의 범위 안에 있는 id를 입력하세요."));
    }

    public void deleteTask(Long id) {
        tasks.remove(findTask(id));
    }

    private Long generateId() {
        if(tasks.size() == 0) {
            return 1L;
        }
        return tasks.get(tasks.size() - 1).getId() + 1L;
    }
}
