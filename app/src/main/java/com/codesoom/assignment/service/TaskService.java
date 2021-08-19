package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.model.TaskRequest;
import com.codesoom.assignment.model.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final long INIT_ID = 0L;
    private static final String TASK_NOT_FOUND = "찾으시는 Task가 존재하지 않습니다.";

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
        Task task = task(id);
        return new TaskResponse(task.id(), task.title());
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        long id = taskKey.incrementAndGet();
        Task task = new Task(id, taskRequest.getTitle());
        tasks.put(id, task);
        return new TaskResponse(task.id(), task.title());
    }

    private Task task(Long id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        throw new NoSuchElementException(TASK_NOT_FOUND);
    }
}
