package com.codesoom.assignment.application;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    List<Task> tasks = new ArrayList<>();
    private Long taskId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }


    public Task getTask(Long id) {
        return tasks.stream().filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask(Long id) {
        Task task = getTask(id);
        tasks.remove(task);
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    private synchronized Long generateId() {
        taskId += 1;
        return taskId;
    }

    public Task updateTask(Long id, Task sourceTask) {
        Task task = getTask(id);
        task.setTitle(sourceTask.getTitle());
        return task;
    }
}
