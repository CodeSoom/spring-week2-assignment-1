package com.codesoom.assignment.repositories;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("There is no task with that id"));
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
