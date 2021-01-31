package com.codesoom.assignment.service;

import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private HashMap<Long, Task> tasks = new HashMap();
    private Long newId = 0L;

    @Override
    public List<Task> getTaskList() {
        return tasks
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Task createTask(Task task) {
        Long id = generateId();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    @Override
    public Task getTask(Long id) {
        Task task = findTask(id);
        return task;
    }

    @Override
    public Task modifyTask(Task source, Long id) {
        Task task = findTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = findTask(id);
        tasks.remove(task);
    }

    private Task findTask(Long id) {
        return tasks.get(id);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
