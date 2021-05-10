package com.codesoom.assignment.repositories;

import com.codesoom.assignment.dto.Task;
import com.codesoom.assignment.exceptions.DoesNotExistException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private final List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> findAll() {
        return this.tasks;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task getOneTask(Long id) {
        return this.tasks.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(DoesNotExistException::new);
    }
}
