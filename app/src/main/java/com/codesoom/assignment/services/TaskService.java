package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    List<Task> tasks = new ArrayList<>();
    Long id = 0L;

    public List<Task> readTasks() {
        return tasks;
    }

    public Task readTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Task createTask(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setId(generateId());
        tasks.add(newTask);
        return newTask;
    }

    public void deleteTask(Long id) {
        Task task = readTask(id);
        tasks.remove(task);
    }

    public Task putOrPatchTask(@PathVariable Long id, @RequestBody Task task) {
        Task updateTask = readTask(id);
        updateTask.setTitle(task.getTitle());
        return updateTask;
    }

    public Long generateId(){
        id += 1;
        return id;
    }

}
