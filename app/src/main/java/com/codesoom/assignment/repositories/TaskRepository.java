package com.codesoom.assignment.repositories;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }


    public void deleteTask(Task task) {
        if(!tasks.contains(task)) {
          //  throw new TaskNotFoundException();
        }
        tasks.remove(task);
    }


    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
