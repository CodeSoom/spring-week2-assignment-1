package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.RepositoryOutput;

import java.util.Map;

public class TaskRepositoryOutput implements RepositoryOutput {
    private final Map<Long, Task> tasks;
    private Long sequence;
    private Long savedTaskId;
    private Long updatedTaskId;

    public TaskRepositoryOutput(Map<Long, Task> tasks, Long sequence) {
        this.tasks = tasks;
        this.sequence = sequence;
    }

    @Override
    public void save(Task task) {
        final Task savingTask = new Task(sequence, task.title());
        tasks.put(sequence, savingTask);
        savedTaskId = sequence;
        sequence += 1;
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
