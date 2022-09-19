package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskException;
import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(throwTaskException());
    }

    public Task insertTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task newTask) {
        return taskRepository.update(id, newTask).orElseThrow(throwTaskException());

    }

    public void deleteTask(Long id) {
        taskRepository.delete(id).orElseThrow(throwTaskException());
    }

    private Supplier<TaskException> throwTaskException() {
        return () -> new TaskException("존재하지않는 아이디입니다.");
    }
}
