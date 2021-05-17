package com.codesoom.assignment.application;

import com.codesoom.assignment.exception.ParameterEmptyException;
import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        return findTask(id);
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskIdNotFoundException::new);
    }

    private Long generatedId() throws Throwable {
        Long maxId = 1L;
        if (tasks.size() > 0) {
            Comparator<Task> comparator = Comparator.comparingLong(Task::getId);
            maxId = tasks.stream()
                    .max(comparator)
                    .orElseThrow(TaskIdNotFoundException::new)
                    .getId() + 1;
        }
        return maxId;
    }

    private void validateParameter(Long id, Task task) {
        final String newTitle = task.getTitle();
        if (id == null || newTitle.isBlank()) {
            throw new ParameterEmptyException(id, newTitle);
        }
    }

    public void createTask(Task task) throws Throwable {
        task.setId(generatedId());
        tasks.add(task);
    }

    public void removeTask(Long id) {
        tasks.remove(findTask(id));
    }

    public Task updateTask(Long id, Task task) {
        validateParameter(id, task);
        Task foundTask = findTask(id);
        int foundTaskIndex = tasks.indexOf(foundTask);
        task.setId(foundTask.getId());
        tasks.set(foundTaskIndex, task);
        return task;
    }
}
