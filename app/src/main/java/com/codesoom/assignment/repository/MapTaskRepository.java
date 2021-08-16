package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapTaskRepository implements TaskRepository {

    private Map<Long, Task> tasks = new HashMap<>();
    private Long id = 1L;

    @Override
    public Task join(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);

        return task;
    }

    @Override
    public Task findTask(String findId) {
        return tasks.get(Long.parseLong(findId));
    }

    @Override
    public Map findAll(){
        return tasks;
    }

    @Override
    public Task updateTask(String findId, Task task) {
        Task editTask = findTask(findId);
        editTask.setTitle(task.getTitle());

        return editTask;
    }

    @Override
    public void deleteTask(String findId) {
        tasks.remove(Long.parseLong(findId));
    }

}
