// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Tasks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private Tasks tasks = new Tasks();
    private Long newId = 0L;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        return tasks.getAllTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable Long id) { return  tasks.findTask(id); }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return tasks.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) { tasks.deleteTask(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) { return tasks.addTask(task); }
}
