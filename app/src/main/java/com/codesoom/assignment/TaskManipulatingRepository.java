package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.ManipulatingRepository;

import java.util.Map;

public class TaskManipulatingRepository implements ManipulatingRepository {
    private final Map<Long, Task> tasks;
    private Long savedTaskId;
    private Long updatedTaskId;

    public TaskManipulatingRepository(Map<Long, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void save(Task task) {
        final Task savingTask = new Task(task.title());
        tasks.put(savingTask.id(), savingTask);
        savedTaskId = savingTask.id();
    }

    @Override
    public void update(Long oldTaskId, Task newTask) {
        final Task updatingTask = new Task(oldTaskId, newTask.title());
        tasks.put(oldTaskId, updatingTask);
        updatedTaskId = oldTaskId;
    }

    @Override
    public void deleteBy(Long id) {
        tasks.remove(id);
    }

    @Override
    public Task taskSaved() {
        return tasks.get(savedTaskId);
    }

    @Override
    public Task taskUpdated() {
        return tasks.get(updatedTaskId);
    }
}
