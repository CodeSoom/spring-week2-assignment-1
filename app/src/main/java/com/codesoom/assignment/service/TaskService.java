package com.codesoom.assignment.service;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    public List<Task> getTasks(){
        return tasks;
    }

    public Task getTask(Long id){
        Task task = findTask(id);
        return task;
    }

    public Task createTask(Task task){
        task.setId(id++);
        tasks.add(task);

        return task;
    }

    public Task updateTask(Task task){
        Task updateTask = findTask(task.getId());
        updateTask.setTitle(task.getTitle());

        return task;
    }

    public Task deleteTask(Long id){
        Task deleteTask = findTask(id);
        tasks.remove(deleteTask);

        return deleteTask;
    }

    public Task findTask(Long id){
        return Optional.ofNullable(
                tasks.stream()
                     .filter(t -> t.getId().equals(id))
                     .findFirst()
                     .orElse(null))
                     .orElseThrow(TaskNotFoundException::new);
    }
}
