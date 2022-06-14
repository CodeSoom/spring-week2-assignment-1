package com.codesoom.todo.services;

import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();


    public Task addTask(Task task){
        taskRepository.add(task);
        return task;
    }

    public Optional<Task> showTask(Long id){
        return taskRepository.findById(id);
    }

    public List<Task> showTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> editTaskTitle(Task newTask){
        return taskRepository.edit(newTask);
    }

    public Optional<Task> removeTask(Long id){
        return taskRepository.findById(id);
    }
}
