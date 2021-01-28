package com.codesoom.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public Task getTask(Long id) throws NoSuchElementException {
        return taskList.stream().filter(task -> Objects.equals(id, task.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Task add(Task task) throws UnknownError {
        task.setId(generateId());

        if (taskList.add(task)) {
            return task;
        }

        throw new UnknownError();
    }

    public Task modify(Long id, String title) throws NoSuchElementException {
        Task task = getTask(id);
        int index = taskList.indexOf(task);

        if (index < 0) {
            throw new NoSuchElementException();
        }

        task.setTitle(title);
        return taskList.set(index, task);
    }

    public boolean remove(Long id) {
        return taskList.removeIf(task -> Objects.equals(id, task.getId()));
    }

    private Long generateId() {
        return taskList.stream().mapToLong(task -> task.getId()).max().orElse(0) + 1;
    }
}
