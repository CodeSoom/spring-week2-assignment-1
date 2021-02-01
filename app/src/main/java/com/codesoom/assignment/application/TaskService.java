package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.TaskNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new LinkedHashMap<>();
    private final IdGenerable taskIdGenerator;

    private TaskService(IdGenerable taskIdGenerator) {
        this.taskIdGenerator = taskIdGenerator;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(long id) {
        return getTaskById(id);
    }

    public Task createNewTask(String title) {
        Task task = new Task(taskIdGenerator.generateNewTaskId(), title);
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
        return findTaskById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    private Optional<Task> findTaskById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public void clearTasks() {
        tasks.clear();
        taskIdGenerator.resetId();
    }
}
