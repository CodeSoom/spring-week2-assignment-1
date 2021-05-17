package com.codesoom.assignment.application;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private List<Task> taskList = new ArrayList<>();
    private Long newTaskId = 0L;

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(Long taskId) {
        return taskList.stream()
                       .filter(task -> task.getId().equals(taskId))
                       .findFirst()
                       .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        taskList.add(task);

        return task;
    }

    public Task updateTask(Long taskId, Task requestTask) {
        Task searchTask = getTask(taskId);
        searchTask.setTitle(requestTask.getTitle());

        return searchTask;
    }

    public void removeTask(Long taskId) {
        Task searchTask = getTask(taskId);
        taskList.remove(searchTask);
    }

    private synchronized Long generateId() {
        newTaskId = newTaskId + 1;
        return newTaskId;
    }
}
