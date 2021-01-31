package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.TaskRepository;
import com.codesoom.assignment.optional.Empty;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskApplicationService {
    private final TaskRepository taskRepository = new TaskRepository();

    public List<Task> getAllTasks() {
        List<Task> allTasks = taskRepository.getAllTask();
        orderTaskById(allTasks);
        return allTasks;
    }

    public Long createTask(String title) {
        Long id = taskRepository.nextId();
        Task newTask = new Task(id, title);
        taskRepository.save(newTask);
        return id;
    }

    public Optional<Task> findTask(Long taskId) {
        return taskRepository.getById(taskId);
    }


    public Optional<Empty> updateTaskTitle(Long taskId, String newTitle) {
        Optional<Task> targetTask = findTask(taskId).map(
                it -> it.updateTaskTitle(newTitle)
        );
        targetTask.ifPresent(
            taskRepository::save
        );
        return targetTask.map(
            task -> Empty.EMPTY
        );
    }

    public Optional<Empty> deleteTask(Long taskId) {
        Optional<Task> targetTask = findTask(taskId);
        targetTask.ifPresent(
            taskRepository::delete
        );
        return targetTask.map(
            task -> Empty.EMPTY
        );
    }

    private void orderTaskById(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getId));
    }
}
