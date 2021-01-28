package com.codesoom.assignment.models;

import com.codesoom.assignment.errors.NotFoundTaskIDException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Managing task. Singleton class.
 *
 * @see Task
 */
public class TaskManager {
    private static final TaskManager instance = new TaskManager();
    private static final LinkedHashMap<Long, Task> tasks = new LinkedHashMap<>();
    private static long newID = 0;

    /**
     * Returns unique instance.
     */
    public static TaskManager getInstance() {
        return instance;
    }

    private void increaseNewID() {
        newID += 1;
    }

    private void initializationNewID() {
        newID = 0;
    }

    /**
     * Check target id is exists.
     *
     * @param id is target id.
     * @return true when target id is exist.
     */
    private boolean isExistID(long id) {
        Task task = tasks.get(id);
        return task != null;
    }

    /**
     * Remove all tasks.
     * Make 0 to {@code newID}.
     */
    public void clear() {
        tasks.clear();
        initializationNewID();
    }

    /**
     * Returns all tasks list.
     */
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * @param id is target id.
     * @return task what task's id is target id.
     * @throws NotFoundTaskIDException when not exists target id.
     */
    public Task findOne(long id) throws NotFoundTaskIDException {
        Task task = tasks.get(id);
        if (task == null) {
            throw new NotFoundTaskIDException(id);
        }
        return task;
    }

    /**
     * Insert new task.
     *
     * @param title is new task's title.
     * @return inserted new task.
     */
    public Task insertOne(String title) {
        Task task = new Task(newID, title);
        increaseNewID();

        tasks.put(task.id(), task);
        return task;
    }

    /**
     * Modify one task.
     *
     * @param id    is target id.
     * @param title is want to modify title.
     * @return modified task.
     * @throws NotFoundTaskIDException when not exists target id.
     */
    public Task modifyOne(long id, String title) throws NotFoundTaskIDException {
        if (!isExistID(id)) {
            throw new NotFoundTaskIDException(id);
        }

        Task task = new Task(id, title);
        tasks.replace(id, task);
        return task;
    }

    /**
     * Delete one task.
     *
     * @param id is target id.
     * @return deleted task.
     * @throws NotFoundTaskIDException when not exists target id.
     */
    public Task deleteOne(long id) throws NotFoundTaskIDException {
        Task removedTask = tasks.remove(id);
        if (removedTask == null) {
            throw new NotFoundTaskIDException(id);
        }
        return removedTask;
    }
}
