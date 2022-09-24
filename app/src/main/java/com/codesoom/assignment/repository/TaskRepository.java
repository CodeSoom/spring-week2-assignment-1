package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskRepository {

    public List<Task> findAll();
    public ResponseEntity<Task> readTask(Long id);
    public Task createTask(Task task);
    public ResponseEntity<Task> updateTask(Long id, Task task);
    public ResponseEntity<Task> deleteTask(Long id);



}
