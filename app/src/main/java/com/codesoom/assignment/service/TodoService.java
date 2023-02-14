package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TodoService {
    private final AutoIdService autoIdService = new AutoIdService();
    private final List<Task> tasks = new ArrayList<>();


    public List<Task> readAllTask() {
        return tasks;
    }

    public Task createTask(Task task) {
        task.setId(autoIdService.generateId());
        tasks.add(task);
        return task;
    }

    public void deleteOneTask(Long id) {
        tasks.remove(findById(id));
    }


    private int findById(Long id) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}

