package com.codesoom.assignment.domain;

import java.util.*;

/**
 * {@code Tasks} is the manager of task
 *
 * @author etff
 * @version 1.0.0 21/02/29
 * @see com.codesoom.assignment.domain.Task
 */
public class Tasks {
    private Map<Long, Task> tasks;
    private Long taskId = 1L;

    public Tasks() {
        this.tasks = new LinkedHashMap<>();
    }

    /**
     * Add task to tasks
     *
     * @param task item of task
     */
    public void addTask(Task task) {
        Long generateId = generateId();
        task.setId(generateId);
        tasks.put(generateId, task);
    }

    /**
     * Remove task
     *
     * @param id target id of task
     */
    public void remove(Long id) {
        this.tasks.remove(id);
    }

    /**
     * Find target task
     *
     * @param id target task id
     * @return targeted task
     */
    public Optional<Task> findTask(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    /**
     * Find all saved tasks
     *
     * @return all saved tasks
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * Find size of collection
     *
     * @return size of saved task
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Generate auto increase task id
     *
     * @return task id
     */
    private Long generateId() {
        return taskId++;
    }
}
