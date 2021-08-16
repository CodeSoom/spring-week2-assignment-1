package com.codesoom.assignment.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final List<Map<String, Task>> tasks = new ArrayList<>();
    private final Map<String, Task> taskMap = new HashMap<>();
    private Long id = 0L;

    public List<Map<String, Task>> getTasks() {
        return tasks;
    }

    public ResponseEntity<Task> getTask(Long id) {
        Optional<Task> task = findTask(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    public ResponseEntity<Task> createTask(Task task) {
        task.setId(generateId());
        taskMap.put(task.getId() + "", task);
        if (tasks.isEmpty()) {
            tasks.add(taskMap);
        }

        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    public ResponseEntity<Task> updateTask(Long id, Task task) {
        Optional<Task> findtask = findTask(id);
        if (findtask.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        findtask.get().setTitle(task.getTitle());

        return new ResponseEntity(findtask, HttpStatus.OK);
    }

    public ResponseEntity deleteTask(Long id) {
        Optional<Task> findtask = findTask(id);
        if (findtask.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        taskMap.remove(String.valueOf(id));

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Long generateId() {
        return ++id;
    }

    private Optional<Task> findTask(Long id) {
        Optional<Task> task = Optional.empty();
        Task findTask = taskMap.get(String.valueOf(id));

        if (findTask == null) {
            return task;
        }

        return task.of(findTask);
    }
}
