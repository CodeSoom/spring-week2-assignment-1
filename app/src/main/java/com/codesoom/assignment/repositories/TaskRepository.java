package com.codesoom.assignment.repositories;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public final class TaskRepository {
    private static final List<Task> TASKS = new ArrayList<>();
    private static final int INDEX_START = 0;

    private static Long NEW_ID = 0L;

    private static Long generateId() {
        return ++NEW_ID;
    }

    public static List<Task> getTasks() {
        return TASKS;
    }

    public synchronized static Task createTask(final TaskDTO taskDTO) {
        final Task task = new Task(generateId(), taskDTO.getTitle());
        TASKS.add(task);
        return task;
    }

    public static OptionalInt findTaskIndex(final Long id) {
        return IntStream.range(INDEX_START, TASKS.size())
                .filter(index -> Objects.equals(TASKS.get(index).getId(), id))
                .findFirst();
    }

    public static Task getTask(final int index) {
        return TASKS.get(index);
    }

    public synchronized static Task updateTask(final int index, final TaskDTO taskDTO) {
        final Task task = TASKS.get(index);
        task.setTitle(taskDTO.getTitle());
        return task;
    }

    public synchronized static void deleteTask(final int index) {
        TASKS.remove(index);
    }


}
