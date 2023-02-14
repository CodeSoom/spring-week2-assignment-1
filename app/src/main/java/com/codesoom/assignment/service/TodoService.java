package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TodoService {
    private final IdService idService = new IdService();

    private final List<Task> tasks = new ArrayList<>();


    public List<Task> readAllTask() {
        return tasks;
    }

    public Task createTask(Task task) {
        blankRedundantExceptionHandling(task);
        task.setId(idService.generateId());
        tasks.add(task);
        return task;

    }

    private void blankRedundantExceptionHandling(Task task) {
        if (task.getTitle().isBlank()) {
            throw new IllegalStateException("Please Input task text");
        }

        for (Task task1 : tasks) {
            if (task1.getTitle().equals(task.getTitle())) {
                throw new IllegalStateException("같은 할 일이 있습니다.");
            }
        }
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


