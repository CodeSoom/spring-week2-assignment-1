package com.codesoom.assignment.models;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> readAll() {
        return tasks;
    }

    public Task read(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public Task create(Task task) {
        if (task.getTitle().isBlank()) {
            // TODO error...
        }

        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task update(Long id, String title) {
        Task editTask = new Task(id, title);
        int index = 0;
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                break;
            }
            index++;
        }
        tasks.set(index, editTask);
        return editTask;
    }

    public void delete(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    /**
     * 새로운 Id를 생성하여 반환합니다.
     * @return 새로운 Id
     */
    private Long generateId() {
        newId += 1;
        return newId;
    }
}
