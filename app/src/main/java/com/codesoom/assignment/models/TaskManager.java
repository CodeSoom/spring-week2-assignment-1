package com.codesoom.assignment.models;

import com.codesoom.assignment.errors.NotFoundTaskIDException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private static final TaskManager manager = new TaskManager();
    private static final HashMap<Long, Task> tasks = new HashMap<>();
    private static long newID = 0;

    public static TaskManager getInstance() {
        return manager;
    }

    private void increaseNewID() {
        newID += 1;
    }

    private void initializationNewID() {
        newID = 0;
    }

    private boolean isExistID(long id) {
        Task task = tasks.remove(id);
        return task != null;
    }

    public void clear() {
        tasks.clear();
        initializationNewID();
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findOne(long id) throws NotFoundTaskIDException {
        Task task = tasks.get(id);
        if (task == null) {
            throw new NotFoundTaskIDException(id);
        }
        return task;
    }

    public Task insertOne(String title) {
        Task task = new Task(newID, title);
        increaseNewID();

        tasks.put(task.id(), task);
        return task;
    }

    public Task modifyOne(long id, String title) throws NotFoundTaskIDException {
        if (!isExistID(id)) {
            throw new NotFoundTaskIDException(id);
        }

        Task task = new Task(id, title);
        tasks.replace(id, task);
        return task;
    }

    public Task deleteOne(long id) throws NotFoundTaskIDException {
        Task removedTask = tasks.remove(id);
        if (removedTask == null) {
            throw new NotFoundTaskIDException(id);
        }
        return removedTask;
    }
}
