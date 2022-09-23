package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Task> findTaskId(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    public Optional<Task> createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return taskRepository.update(id, task);
    }

    public ResponseEntity<Task> deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }
}
