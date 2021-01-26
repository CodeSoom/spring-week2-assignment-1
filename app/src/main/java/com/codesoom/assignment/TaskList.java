package com.codesoom.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Task model collection to manage tasks.
 * add, modify, delete task and fetch task or task list
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<Task>();

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(Long id) {
        return taskList.stream().filter(task -> Objects.equals(id, task.getId())).findFirst().orElse(new Task());
    }

    public Task add(String title) {
        Long newId = Long.valueOf(taskList.size());
        Task task = new Task(newId, title);
        return taskList.add(task) ? task : null;
    }

    public Task modify(Long id, String title) {
        Task task = getTask(id);
        int index = taskList.indexOf(task);

        if (index < 0) {
            return null;
        }

        task.setTitle(title);
        return taskList.set(index, task);
    }

    public boolean remove(Long id) {
        return taskList.removeIf(task -> Objects.equals(id, task.getId()));
    }
}
