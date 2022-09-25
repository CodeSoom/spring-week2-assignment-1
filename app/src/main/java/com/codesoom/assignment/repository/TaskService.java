package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The class is responsible for CRUD for Task.
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * returns ResponseEntity with the given id.
     * finds a task with the id from the database.
     * @param id from URI parameter
     * @return a responseEntity with HttpStatus.OK; otherwise, responseEntity with httpStatus.NO_CONTENT
     */
    public ResponseEntity<Task> findTaskId(Long id) {
        return taskRepository.findById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }

    /**
     *  creates a task and store in the database and returns the optional Task from the database.
     * @param task
     * @return
     */
    public Optional<Task> createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * finds all tasks the tasks and returns the list of tasks.
     * @return the list of task from the repository
     */
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * updates the title and returns the ResponseEntity with the approriate status code.
     * @param id from URI parameter
     * @param task from response body
     * @return ResponseEntity with HttpStatus.OK; otherwise, ResponseEntity with HttpStatus.NOT_FOUND
     */
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return taskRepository.update(id, task).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }

    /**
     * deletes the task from the database
     * @param id from URI parameter
     * @return ResponseEntity with the Http.NO_CONTENT; otherwise, ResponseEntity with HttpStatus.NOT_FOUND
     */
    public ResponseEntity<Task> deleteTask(Long id) {
        return taskRepository.deleteById(id).map(value -> new ResponseEntity<>(value, HttpStatus.NO_CONTENT)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }
}
