package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskRepository {

    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task createTask(Task task) {
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task task) {
        tasks.stream()
                .filter(target -> target.getId().equals(id))
                .findFirst().ifPresent(targetTask -> targetTask.setTitle(task.getTitle()));

        return task;
    }

    public void deleteTask(Long id) {
        Task task = getTask(id);
        tasks.remove(task);
    }
}
