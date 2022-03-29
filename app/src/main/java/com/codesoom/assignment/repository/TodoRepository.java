package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository {

    private List<Task> tasks = new ArrayList<>();
    private Long lastId = 0L;

    private synchronized Long getNewId() {
        lastId += 1L;
        return lastId;
    }

    public List<Task> findAllTasks() {
        return tasks;
    }

    public Optional<Task> findTaskById(Long id) {
        return tasks.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }

    public Task save(Task task) {
        if(task.getId() == null) task.setId(getNewId());
        tasks.add(task);
        return task;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

}
