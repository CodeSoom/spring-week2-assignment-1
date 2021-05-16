package com.codesoom.assignment.application;

import com.codesoom.assignment.exceptions.TaskTitleBlankException;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks;
    private Long newId = 0L;

    public InMemoryTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public List<Task> fetchAll() {
        return tasks;
    }

    @Override
    public Task fetchOne(Long id) {
        return tasks.stream()
                .filter((Task task) -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public List<Task> createOne(Task task) {
        if (task.getTitle().isBlank()) {
            throw new TaskTitleBlankException();
        }

        task.setId(generateId());
        tasks.add(task);
        return tasks;
    }

    @Override
    public Task updateOne(Long id, Task source) {
        Task task = fetchOne(id);
        task.setTitle(source.getTitle());
        return task;
    }

    @Override
    public void deleteOne(Long id) {
        Task task = fetchOne(id);
        tasks.remove(task);
    }

    @Override
    public synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
