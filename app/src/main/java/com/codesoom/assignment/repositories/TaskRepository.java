package com.codesoom.assignment.repositories;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.models.Task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public final class TaskRepository {
    private static final List<Task> TASKS = new ArrayList<>();
    private static Long newId = 0L;

    private Long generateId() {
        return ++newId;
    }

    public List<Task> getTasks() {
        return TASKS;
    }

    public synchronized Task createTask(final TaskDTO taskDTO) {
        final Task task = new Task(generateId(), taskDTO.getTitle());
        TASKS.add(task);
        return task;
    }

    public OptionalInt findTaskIndex(final Long id) {
        return IntStream.range(0, TASKS.size())
                .filter(index -> Objects.equals(TASKS.get(index).getId(), id))
                .findFirst();
    }

    public Task getTask(final int index) {
        return TASKS.get(index);
    }

    public synchronized Task updateTask(final int index, final TaskDTO taskDTO) {
        final Task task = TASKS.get(index);
        task.setTitle(taskDTO.getTitle());
        return task;
    }

    public synchronized void deleteTask(final int index) {
        TASKS.remove(index);
    }
}
