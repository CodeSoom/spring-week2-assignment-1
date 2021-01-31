// TODO
// 1. Read Collection - GET /tasks      => 완료
// 2. Read Item - GET /tasks/{id}       => 완료
// 3. Create - POST /tasks              => 완료
// 4. Update - PUT/PATCH /tasks/{id}    => 완료
// 5. Delete - DELETE /tasks/{id}       => 완료

package com.codesoom.assignment.controllers;


import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TaskService taskService;
    public TaskController() {
        taskService = new TaskService();
    }

    @GetMapping()
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @RequestMapping(path = "{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // Todo : Create a successful deletion message
        taskService.deleteTask(id);
    }
}
