package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Task Respository class with CRUD operations
 */
@Repository
public class TaskRepository {

    private ConcurrentHashMap<Long, Task> tasksHash = new ConcurrentHashMap<>();

    /**
     * return Optional Task.
     * @param id from URI param
     * @return Optional task; otherwise, empty option
     */
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasksHash.getOrDefault(id, null));
    }

    /**
     * stores a new task  and returns optional task.
     * @param task
     * @return saved optional task; otherwise, empty optional
     */
    public Optional<Task> save(Task task) {
        Task newTask = new Task(IdGenerator.generateId(), task.getTitle());
        tasksHash.put(newTask.getId(), newTask);
        return Optional.ofNullable(newTask);
    }

    /**
     * returns all the list of tasks.
     * @return the list of all tasks
     */
    public List<Task> findAll() {
        return new ArrayList<>(
                tasksHash.values());
    }

    /**
     * updates the task and returns the optional task.
     * @param id from URI parameter
     * @param task from the request body
     * @return updated optional task; otherwise, returns optional empty
     */
    public Optional<Task> update(Long id, Task task) {
        if (tasksHash.containsKey(id)) {
            Task updatedTask = tasksHash.get(id).updateTitle(task.getTitle());
            tasksHash.put(id, updatedTask);
            return Optional.ofNullable(updatedTask);
        }
        return Optional.empty();
    }

    /**
     * deletes the task from the database.
     * @param id from URI param
     * @return the deleted optional task; otherwise, optional empty
     */
    public Optional<Task> deleteById(Long id) {
        if (tasksHash.containsKey(id)) {
            return Optional.ofNullable(tasksHash.remove(id));
        }
        return Optional.empty();
    }
}
