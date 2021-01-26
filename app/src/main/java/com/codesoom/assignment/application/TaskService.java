package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TaskService {
    private Map<Long, Task> tasks = new HashMap<>();
    private int idCounter = 0;

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(long id) {
        return getTaskById(id);
    }

    public Task createNewTask(String title) {
        Task task = new Task(idCounter++, title);
        tasks.put(task.getId(), task);
        System.out.println("Completed to create task - " + task.toString());
        return task;
    }

    public Task updateTask(long id, String newTitle) {
        Task task = getTaskById(id);
        task.setTitle(newTitle);
        System.out.println("Completed to update task - " + task.toString());
        return task;
    }

    public void deleteTask(long id) {
        Task task = getTaskById(id);
        tasks.remove(task.getId());
    }


    private Task getTaskById(long id) throws IllegalArgumentException {
        return findTaskById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private Optional<Task> findTaskById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public void clearTasks() {
        tasks.clear();
        idCounter = 0;
    }
}
