package com.codesoom.assignment.controllers;

// 1. Read Collection - GET /tasks => Done
// 2. Read Item - GET /tasks/{id} => WIP
// 3. Create - POST /tasks => Done
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.system.InvalidRequestException;
import com.codesoom.assignment.system.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    // GET /tasks
    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    // POST /tasks
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            // TODO: validation error...
            throw new InvalidRequestException();
        }

        task.setId(generateID());
        tasks.add(task);

        return task;
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable("id") Long id) {
        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            return tasks.stream().filter(t -> t.getId().equals(id))
                    .findFirst().get();
        }
    }



    // PUT/PATCH /tasks/{id}
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task patchTask(@PathVariable("id") Long id, @RequestBody Task newTask) {

        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            Task task = tasks.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .get();

            task.setTitle(newTask.getTitle());
            return task;
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task putTask(@PathVariable("id") Long id, @RequestBody Task newTask) {

        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            Task task = tasks.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .get();

            task.setTitle(newTask.getTitle());
            return task;
        }
    }

    // DELETE /tasks{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            Task task = tasks.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .get();

            tasks.remove(task);
        }
    }

    private Long generateID() {
        newId += 1;
        return newId;
    }

    private <Task> Stream<Task> collectionToStream(List<Task> tasks) {
        return Optional
                .ofNullable(tasks)
                .map(List::stream)
                .orElseGet(Stream::empty);
    }

    private boolean emptyCheck(Long id) {
        return collectionToStream(tasks)
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .isEmpty();
    }
}