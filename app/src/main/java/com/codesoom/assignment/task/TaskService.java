package com.codesoom.assignment.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }



    public List<Task> getTasks() {
        return taskRepository.tasks;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    public Task createTask(Task task) {
        Task newTask = new Task(taskRepository.nextId(), task.getTitle());
        taskRepository.tasks.add(newTask);
        return newTask;
    }
}
