package com.codesoom.assignment.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        int whichOne = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .orElseThrow();
        Task editTask = new Task(id, title);
        tasks.set(whichOne, editTask);
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
