package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private ConcurrentHashMap<Long, Task> tasksHash = new ConcurrentHashMap<>();

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasksHash.getOrDefault(id, null));
    }

    public Optional<Task> save(Task task) {
        Task newTask = new Task(IdGenerator.generateId(), task.getTitle());
        tasksHash.put(newTask.getId(),newTask );
        return Optional.ofNullable(newTask);
    }

    public List<Task> findAll() {
        return new ArrayList<>(
                tasksHash.values());
    }

    public ResponseEntity<Task> update(Long id, Task task) {
        if (tasksHash.containsKey(id)) {
            Task getTask = tasksHash.get(id);
            getTask.updateTitle(task.getTitle());

            return new ResponseEntity<>(getTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Task> deleteById(Long id) {
        if (tasksHash.containsKey(id)) {
            tasksHash.remove(id);
            return new ResponseEntity<>(tasksHash.get(id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }
}
