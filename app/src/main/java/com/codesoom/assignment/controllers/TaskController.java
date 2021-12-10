//TODO
// 1. Read Collection - GET /tasks => 완로
// 2. Read Item - GET /tasks/{id} => 완료
// 3. Create - POST / tasks => 완료
// 4. Update - PUT/PATH /tasks/{id} => 완료
// 5. Delete - DELETE /tasks/{id} => 완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> detail(@PathVariable Long id) {
        Optional<Task> task = getTask(id);

        return getTaskResponseEntity(task);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Task source) {
        Optional<Task> task = getTask(id);
        task.get().setTitle(source.getTitle());

        return getTaskResponseEntity(task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> task = getTask(id);
        tasks.remove(task.get());

        return getObjectResponseEntity(task);
    }

    private Long generateId() {
        id += 1;
        return id;
    }

    private Optional<Task> getTask(@PathVariable Long id) {
        return tasks.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    private ResponseEntity<Task> getTaskResponseEntity(Optional<Task> task) {
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Optional<Task> task) {
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
