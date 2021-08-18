package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import java.util.Collection;

public class TaskManager {

    private static final TaskManager uniqueInstance = new TaskManager();

    private final TaskMap taskMap = new TaskMap();

    private Long lastId = 0L;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        return uniqueInstance;
    }

    public Task createTask(Task task) {
        Long lastId = getLastId();
        task.setId(lastId);

        taskMap.insert(lastId, task);

        return task;
    }

    public Collection<Task> getAllTasks() {
        return taskMap.getValues();
    }

    public Task getTaskWith(Long id) {
        return taskMap.getWith(id);
    }

    public Task updateTask(Long id, Task content) {
        return taskMap.update(id, content.getTitle());
    }

    private synchronized Long getLastId() {
        increaseLastId();
        return lastId;
    }

    private void increaseLastId() {
        lastId++;
    }

}
