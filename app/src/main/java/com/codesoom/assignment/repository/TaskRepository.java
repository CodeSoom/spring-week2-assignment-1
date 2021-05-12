package com.codesoom.assignment.repository;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> tasks = new ArrayList<>();
    private Long currentTaskId = 0L;

    public List<Task> getAllTask() {
        return tasks;
    }

    public Task addTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task getTask(long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException());
    }

    public Task setAndReturnTask(long id, Task param) {
        Task task = getTask(id);
        task.setTitle(param.getTitle());
        return task;
    }

    public Task patchTask(long id, Task param) {
        Task task = getTask(id);
        task.setTitle(param.getTitle());
        return task;
    }

    public Task deleteTask(long id) {
        Task task = getTask(id);
        tasks.remove(task);
        return task;
    }

    private long generateId() {
        this.currentTaskId += 1;
        return this.currentTaskId;
    }

}
