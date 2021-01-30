package com.codesoom.assignment.models;

import com.codesoom.assignment.errors.NotFoundTaskIDException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Managing task. Singleton class.
 *
 * @see Task
 */
public class TaskManager {
    private static final TaskManager instance = new TaskManager();
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Returns unique instance.
     */
    public static TaskManager getInstance() {
        return instance;
    }

    /**
     * Returns all tasks list.
     */
    public List<Task> findAll() {
        return tasks
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * @param id is target id.
     * @return task what task's id is target id.
     * @throws NotFoundTaskIDException when not exists target id.
     */
    public Task findOne(int id) throws NotFoundTaskIDException {
        if (isExistID(id)) {
            throw new NotFoundTaskIDException(id);
        }

        return tasks.get(id);
    }

    /**
     * Insert new task.
     *
     * @param title is new task's title.
     * @return inserted new task.
     */
    public Task insertOne(String title) {
        int id = generateID();
        Task task = new Task(id, title);

        tasks.add(task);
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
    public Task modifyOne(int id, String title) throws NotFoundTaskIDException {
        if (isExistID(id)) {
            throw new NotFoundTaskIDException(id);
        }

        Task task = new Task(id, title);
        tasks.set(id, task);
        return task;
    }

    /**
     * Delete one task.
     *
     * @param id is target id.
     * @return deleted task.
     * @throws NotFoundTaskIDException when not exists target id.
     */
    public Task deleteOne(int id) throws NotFoundTaskIDException {
        if (isExistID(id)) {
            throw new NotFoundTaskIDException(id);
        }

        Task removedTask = tasks.get(id);
        tasks.set(id, null);
        return removedTask;
    }

    /**
     * Check target id is exists.
     *
     * @param id is target id.
     * @return true when target id is exist.
     */
    private boolean isExistID(int id) {
        if (tasks.size() <= id) {
            return true;
        }

        Task task = tasks.get(id);
        return task == null;
    }

    private int generateID() {
        return tasks.size();
    }

    /**
     * Remove all tasks.
     * Make 0 to {@code newID}.
     */
    public void clear() {
        tasks.clear();
    }
}
