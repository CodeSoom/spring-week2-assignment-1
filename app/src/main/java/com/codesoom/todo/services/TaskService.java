package com.codesoom.todo.services;

import com.codesoom.todo.controllers.TaskNotFoundException;
import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task addTask(Task task) {
        taskRepository.add(task);
        return task;
    }

    public Task showTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> showTasks() {
        return taskRepository.findAll();
    }

    // TODO: Javadoc
    public Task editTaskTitle(Task newTask) {
        taskRepository.findById(newTask.getId()).orElseThrow(() -> new TaskNotFoundException(newTask));
        return taskRepository.edit(newTask);
    }

    // TODO: Javadoc
    public void removeTask(Long id) {
        taskRepository.findById(id).flatMap(taskRepository::delete).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
