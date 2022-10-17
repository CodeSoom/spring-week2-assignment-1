package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskSearchDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private static final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTaskById(TaskSearchDto taskSearchDto) {
        if (taskSearchDto.getId() == null) {
            return null;
        }

        return tasks.stream()
                .filter(task -> task.getId().equals(taskSearchDto.getId()))
                .findFirst()
                .orElse(null);
    }
}
