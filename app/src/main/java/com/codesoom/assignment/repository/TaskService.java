package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business Logic for Task
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * finds a task with the id and returns ResponseEntity with the given id.
     * @param id from URI parameter
     * @return result of the search
     */
    public ResponseEntity<Task> findTaskId(Long id) {
        return taskRepository.findById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }

    /**
     *  creates and returns the task.
     * @param task
     * @return the created Task
     */
    public Optional<Task> createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * finds all tasks the tasks and returns the list of tasks.
     * @return the list of task
     */
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * updates the title and returns the ResponseEntity with the approriate status code.
     * @param id from URI parameter
     * @param task from response body
     * @return the result of the updated Task
     */
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return taskRepository.update(id, task).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }

    /**
     * deletes the task from the database
     * @param id from URI parameter
     * @return the result of the deleted task
     */
    public ResponseEntity<Task> deleteTask(Long id) {
        return taskRepository.deleteById(id).map(value -> new ResponseEntity<>(value, HttpStatus.NO_CONTENT)).orElseGet(() -> new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND));
    }
}
