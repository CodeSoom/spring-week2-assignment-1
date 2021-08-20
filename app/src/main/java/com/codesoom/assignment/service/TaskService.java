package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.enums.HttpMethod;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.TaskRequest;
import com.codesoom.assignment.model.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final long INIT_ID = 0L;
    private final Map<Long, Task> tasks;
    private final AtomicLong taskKey;

    public TaskService() {
        this.tasks = new ConcurrentHashMap<>();
        this.taskKey = new AtomicLong(INIT_ID);
    }

    public List<TaskResponse> taskList() {
        return tasks.values().stream()
                .map(task -> new TaskResponse(task.id(), task.title()))
                .collect(Collectors.toList());
    }

    public TaskResponse getTask(Long id) {
        Task task = task(id, HttpMethod.GET);
        return new TaskResponse(task.id(), task.title());
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        long id = taskKey.incrementAndGet();
        Task task = new Task(id, taskRequest.getTitle());
        tasks.put(id, task);
        return new TaskResponse(task.id(), task.title());
    }

    public TaskResponse modifyTask(TaskRequest taskRequest) {
        Task task = task(taskRequest.getId(), HttpMethod.PUT);
        task.changeTitle(taskRequest.getTitle());
        return new TaskResponse(task.id(), task.title());
    }

    public void deleteTask(Long id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return ;
        }
        throw new TaskNotFoundException(id, HttpMethod.DELETE);
    }

    private Task task(Long id, HttpMethod method) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        throw new TaskNotFoundException(id, method);
    }

}
