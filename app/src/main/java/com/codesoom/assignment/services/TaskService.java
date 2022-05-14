package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.BadRequestException;
import com.codesoom.assignment.exceptions.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTaskItem(Long id) {
        return findTask(id).get();
    }

    public Task addTask(Task task) {
        checkTitle(task.getTitle());

        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task source) {
        checkTitle(source.getTitle());

        Optional<Task> task = findTask(id);
        task.ifPresent(t -> t.setTitle(source.getTitle()));

        return task.get();
    }

    public boolean deleteTask(Long id) {
        if(id == null) {
            throw new NotFoundException();
        }
        Optional<Task> task = findTask(id);

        return tasks.remove(task.get());
    }

    private Optional<Task> findTask(Long id) {
        return Optional.ofNullable(tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new));
    }

    private  Long generateId() {
        newId += 1;
        return newId;
    }

    private void checkTitle(String title) {
        if(title.isBlank()) {
            throw new BadRequestException();
        }
    }
}
