package com.codesoom.assignment.repositories;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public final class TaskRepository {
    private static final TaskRepository TASK_REPOSITORY = new TaskRepository();

    public static TaskRepository getInstance() {
        return TASK_REPOSITORY;
    }

    private final List<Task> tasks;
    private Long newId = 0L;

    private TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    private Long generateId() {
        return ++newId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public synchronized Task createTask(final TaskDTO taskDTO) {
        final Task task = new Task(generateId(), taskDTO.getTitle());
        tasks.add(task);
        return task;
    }

    public OptionalInt findTaskIndex(final Long id) {
        return IntStream.range(0, tasks.size())
                .filter(index -> Objects.equals(tasks.get(index).getId(), id))
                .findFirst();
    }

    public Task getTask(final int index) {
        return tasks.get(index);
    }

    public synchronized Task updateTask(final int index, final TaskDTO taskDTO) {
        final Task task = tasks.get(index);
        task.setTitle(taskDTO.getTitle());
        return task;
    }

    public synchronized void deleteTask(final int index) {
        tasks.remove(index);
    }
}
