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

    public Task updateTask(Task task, Long id) {
        Task currentTask = taskRepository.findTaskById(id);
        currentTask.updateTitle(task.getTitle());
        return currentTask;
    }

    public void deleteTask(Long id) {
        Task deleteTask = taskRepository.findTaskById(id);
        taskRepository.tasks.remove(deleteTask);
    }

}
