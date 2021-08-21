package com.codesoom.assignment.repository;

import com.codesoom.assignment.controllers.TaskNotFoundException;
import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MapTaskRepository implements TaskRepository {

    private Map<Long, Task> tasks = new HashMap<>();
    private Long id = 0L;

    @Override
    public Task join(Task task) {

        id++;
        task.setId(id);
        tasks.put(task.getId(), task);

        return task;

    }

    @Override
    public Task findTask(Long taskId) {

        return tasks.values().stream().filter(task -> task.getId().equals(taskId)).findFirst()
                .orElseThrow(() -> new TaskNotFoundException(taskId));

    }

    @Override
    public Collection<Task> findAll(){

        return tasks.values();

    }

    @Override
    public Task updateTask(Long taskId, Task task) {

        Task foundTask = findTask(taskId);
        foundTask.setTitle(task.getTitle());
        return foundTask;

    }

    @Override
    public void deleteTask(Long taskId) {

        tasks.remove(findTask(taskId));

    }


}
