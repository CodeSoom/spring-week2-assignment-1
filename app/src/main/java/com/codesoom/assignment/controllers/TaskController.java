package com.codesoom.assignment.controllers;

// 1. Read Collection - GET /tasks => Done
// 2. Read Item - GET /tasks/{id} => WIP
// 3. Create - POST /tasks => Done
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.system.ErrorCreate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;



//    @RequestMapping(path = "", method = RequestMethod.GET)
//    public String list() {
//        return "LIST";
//    }

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

        }

        task.setId(generateID());
        tasks.add(task);

        return task;
    }

    // GET /tasks/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Task get(@PathVariable("id") int id) {

        Task task = tasks.stream().filter(t -> t.getId() == id)
                .findFirst().orElse(null);

        if(task == null) {
            throw new ErrorCreate("존재하지 않는 INDEX입니다.");
        }

        return task;
    }

    // PUT/PATCH /tasks/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public Task patchTask(@PathVariable("id") int id, @RequestBody Task newTask) {

        Task task = tasks.stream().filter(t -> t.getId() == id)
                .findFirst().orElse(null);

        if(task == null) {
            throw new ErrorCreate("존재하지 않는 INDEX입니다.");
        }

        task.setTitle(newTask.getTitle());

        return task;
    }


    private long generateID() {
        newId += 1;
        return newId;
    }

}
