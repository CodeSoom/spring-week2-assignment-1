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
    public Task create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            // TODO: validation error...
            throw new InvalidRequestException();
        }

        task.setId(generateID());
        tasks.add(task);

        return task;
    }

    // GET /tasks/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Task get(@PathVariable("id") long id) {
        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            return tasks.stream().filter(t -> t.getId() == id)
                    .findFirst().get();
        }
    }



    // PUT/PATCH /tasks/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public Task patchTask(@PathVariable("id") long id, @RequestBody Task newTask) {

        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            Task task = tasks.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .get();

            task.setTitle(newTask.getTitle());
            return task;
        }
    }

    // DELETE /tasks{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable("id") long id) {
        if(emptyCheck(id)) {
            throw new DataNotFoundException("id");
        } else {
            Task task = tasks.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .get();

            tasks.remove(task);
        }
    }

    private long generateID() {
        newId += 1;
        return newId;
    }

    private <Task> Stream<Task> collectionToStream(List<Task> tasks) {
        return Optional
                .ofNullable(tasks)
                .map(List::stream)
                .orElseGet(Stream::empty);
    }

    private boolean emptyCheck(long id) {
        return collectionToStream(tasks)
                .filter(t -> t.getId() == id)
                .findFirst()
                .isEmpty();
    }
}
