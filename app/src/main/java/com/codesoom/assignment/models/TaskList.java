package com.codesoom.assignment.models;

import com.codesoom.assignment.config.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();
    private int newId = 0;

    public int getId() {
        return newId;
    }

    public synchronized void add(Task task) {
        task.updateId(generatedId());
        taskList.add(task);
    }

    private int generatedId() {
        return newId += 1;
    }

    public List<Task> getItems() {
        return this.taskList;
    }

    public Task get(int id) throws TaskNotFoundException {
        return taskList.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Not Found"));
    }

    public void update(int taskId, String title) throws TaskNotFoundException {
        Task task = get(taskId);
        task.updateTitle(title);
    }

    public boolean delete(int taskId) throws TaskNotFoundException {
        return this.taskList.remove(get(taskId));
    }
}
