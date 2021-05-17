package com.codesoom.assignment.service;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        task.setId(generatedId());
        task.setTitle(task.getTitle());
        tasks.add(task);

        return task;
    }

    private synchronized Long generatedId() {
        newId += 1;
        return newId;
    }

    public Task updateTask(Long id, Task old_task) {
        Task task = findTask(id);
        task.setTitle(old_task.getTitle());

        return task;
    }

    public void deleteTask(Long id) {
        Task task = findTask(id);
        tasks.remove(task);
    }
}
