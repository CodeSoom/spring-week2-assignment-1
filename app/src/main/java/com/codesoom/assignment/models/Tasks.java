package com.codesoom.assignment.models;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task findTask(Long id) {
        return  tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task updateTask(Long id, Task task) {
        Task temp = findTask(id);
        temp.setTitle(task.getTitle());
        return temp;
    }

    public void deleteTask(Long id) {
        tasks.remove(findTask(id));
    }
}
